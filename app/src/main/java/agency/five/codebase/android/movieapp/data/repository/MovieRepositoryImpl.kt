package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.data.database.DbFavoriteMovie
import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl (
    private val movieService: MovieService,
    private val movieDao: FavoriteMovieDao,
    private val bgDispatcher: CoroutineDispatcher,
    private val dbFavoriteMovie: DbFavoriteMovie
) : MovieRepository{

    private val moviesByCategory: Map<MovieCategory, Flow<List<Movie>>> = MovieCategory.values()
        .associateWith { movieCategory ->
            flow {
                val movieResponse = when (movieCategory){
                    MovieCategory.POPULAR_STREAMING -> R.string.streaming
                    MovieCategory.POPULAR_ON_TV -> R.string.on_tv
                    MovieCategory.POPULAR_FOR_RENT -> R.string.for_rent
                    MovieCategory.POPULAR_IN_THEATRES -> R.string.in_theatres
                    MovieCategory.NOW_PLAYING_MOVIES -> R.string.movies
                    MovieCategory.NOW_PLAYING_TV -> R.string.tv
                    MovieCategory.UPCOMING_TODAY -> R.string.today
                    MovieCategory.UPCOMING_THIS_WEEK -> R.string.this_week
                }
                emit(movieResponse.movies)
            }.flatMapLatest { apiMovies ->
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


    override fun movieDetails(movieId: Int): Flow<MovieDetails> = flow {
        emit( movieService.fetchMovieDetails(movieId) to movieService.fetchMovieCredits(movieId))
    }.flatMapLatest { (apiMovieDetails, apiMovieCredits) ->
        movieDao.favorites()
            .map { favoriteMovies ->
                apiMovieDetails.toMovieDetails(
                    isFavorite = favoriteMovies.any{ it.movieId == apiMovieDetails.id },
                    crew = apiMovieCredits.crew,
                    cast = apiMovieCredits.cast,
                )
            }
    }.flowOn(bgDispatcher)

    override fun favoriteMovies(): Flow<List<Movie>> = favorites

    override suspend fun addMovieToFavorites(movieId: Int) {
        movieDao.insertIntoFavorites(movieId)
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        movieDao.deleteFromFavorites(movieId)
    }

    override suspend fun toggleFavorite(movieId: Int) {
        if(dbFavoriteMovie.movieId == movieId){
            removeMovieFromFavorites(movieId)
        }else{
            addMovieToFavorites(movieId)
        }
    }
}
