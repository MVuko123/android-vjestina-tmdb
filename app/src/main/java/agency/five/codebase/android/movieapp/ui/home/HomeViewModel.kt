package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.constraintlayout.compose.Dimension.Companion.value
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    val homeScreenMapper: HomeScreenMapper,
) : ViewModel() {

    private val initialHomeMovieCategoryViewState =
        HomeMovieCategoryViewState(emptyList(), emptyList())

    private val _popularMoviesHomeViewState = MutableStateFlow(MovieCategory.POPULAR_STREAMING)
    val popularMoviesHomeViewState = _popularMoviesHomeViewState.flatMapLatest { category ->
        movieRepository.movies(category).map { movies ->
            homeScreenMapper.toHomeMovieCategoryViewState(popularCategory, category, movies)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = initialHomeMovieCategoryViewState
    )
    private val _nowPlayingMoviesHomeViewState = MutableStateFlow(MovieCategory.NOW_PLAYING_MOVIES)
    val nowPlayingMoviesHomeViewState= _nowPlayingMoviesHomeViewState.flatMapLatest { category ->
        movieRepository.movies(category).map { movies ->
            homeScreenMapper.toHomeMovieCategoryViewState(nowPlayingCategory, category, movies)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = initialHomeMovieCategoryViewState
    )

    private val _upcomingMovieHomeViewState = MutableStateFlow(MovieCategory.UPCOMING_TODAY)
    val upcomingMovieHomeViewState= _upcomingMovieHomeViewState.flatMapLatest { category ->
        movieRepository.movies(category).map { movies ->
            homeScreenMapper.toHomeMovieCategoryViewState(upcomingCategory, category, movies)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000L),
        initialValue = initialHomeMovieCategoryViewState
    )

    fun toggleFav(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

    fun switchSelectedCategory(categoryId: Int) {
        when (categoryId) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_IN_THEATRES.ordinal
            -> {
                _popularMoviesHomeViewState.update { MovieCategory.values()[categoryId] }
            }
            MovieCategory.NOW_PLAYING_MOVIES.ordinal,
            MovieCategory.NOW_PLAYING_TV.ordinal
            -> {
                _nowPlayingMoviesHomeViewState.update { MovieCategory.values()[categoryId] }
            }
            else -> {
                _upcomingMovieHomeViewState.update { MovieCategory.values()[categoryId] }
            }
        }
    }
}
