package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.data.database.DbFavoriteMovie
import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MovieRepositoryImpl (
    private val movieService: MovieService,
    private val movieDao: FavoriteMovieDao,
    private val bgDispatcher: CoroutineDispatcher,
) : MovieRepository{

    @OptIn(ExperimentalCoroutinesApi::class)
    private val moviesByCategory: Map<MovieCategory, Flow<List<Movie>>> = MovieCategory.values()
        .associateWith { movieCategory ->
            flow {
                val movieResponse = when (movieCategory){
                    MovieCategory.POPULAR_STREAMING -> movieService.fetchPopularMovies()
                    MovieCategory.POPULAR_ON_TV -> movieService.fetchUpcomingMovies()
                    MovieCategory.POPULAR_FOR_RENT -> movieService.fetchTopRatedMovies()
                    MovieCategory.POPULAR_IN_THEATRES -> movieService.fetchNowPlayingMovies()
                    MovieCategory.NOW_PLAYING_MOVIES -> movieService.fetchUpcomingMovies()
                    MovieCategory.NOW_PLAYING_TV -> movieService.fetchPopularMovies()
                    MovieCategory.UPCOMING_TODAY -> movieService.fetchUpcomingMovies()
                    MovieCategory.UPCOMING_THIS_WEEK -> movieService.fetchPopularMovies()
                }
                emit(movieResponse.movies)
            }.flatMapLatest {  apiMovies ->
                movieDao.favorites()
                    .map {  favoriteMovies ->
                        apiMovies.map { apiMovie ->
                            apiMovie.toMovie(isFavorite = favoriteMovies.any{it.movieId == apiMovie.id})
                        }
                    }
            }.shareIn(
                scope = CoroutineScope(bgDispatcher),
                started = SharingStarted.WhileSubscribed(1000L),
                replay = 1
            )
        }

    private val favorites = movieDao.favorites().map {
        it.map { dbFavoriteMovie ->
            Movie(
                id = dbFavoriteMovie.movieId,
                imageUrl = dbFavoriteMovie.posterUrl,
                title = "",
                overview = "",
                isFavorite = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    override fun movies(movieCategory: MovieCategory): Flow<List<Movie>> = moviesByCategory[movieCategory]!!

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun movieDetails(movieId: Int): Flow<MovieDetails> = flow {
        emit( movieService.fetchMovieDetails(movieId) to movieService.fetchMovieCredits(movieId))
    }.flatMapLatest { (apiMovieDetails, apiMovieCredits) ->
        movieDao.favorites()
            .map { favoriteMovies ->
                apiMovieDetails.toMovieDetails(
                    isFavorite = favoriteMovies.any { it.movieId == apiMovieDetails.id },
                    crew = apiMovieCredits.crew,
                    cast = apiMovieCredits.cast,
                )
            }
    }.flowOn(bgDispatcher)

    override fun favoriteMovies(): Flow<List<Movie>> = favorites

    override suspend fun addMovieToFavorites(movieId: Int) {
        runBlocking(bgDispatcher) {
            movieDao.insertIntoFavorites(
                DbFavoriteMovie(
                    movieId,
                    "$BASE_IMAGE_URL/${movieService.fetchMovieDetails(movieId).posterPath}"
                )
            )
        }
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        runBlocking(bgDispatcher) {
            movieDao.deleteFromFavorites(movieId)
        }
    }

    private suspend fun findMovie(movieId: Int): Movie {
        lateinit var movie: Movie
        moviesByCategory.values.forEach { value ->
            val movies = value.first()
            movies.forEach {
                if (it.id == movieId) {
                    movie = it
                }
            }
        }
        return movie
    }

    override suspend fun toggleFavorite(movieId: Int) {
        runBlocking(bgDispatcher) {
            val favoriteMovies = findMovie(movieId)
            if (favoriteMovies?.isFavorite == true) {
                removeMovieFromFavorites(movieId)
            } else {
                addMovieToFavorites(movieId)
            }
        }
    }
    //runBlocking - Runs a new coroutine and blocks the current thread interruptibly until its completion.
}
