package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.constraintlayout.compose.Dimension.Companion.value
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    //private val movieService: MovieService,
    private val movieRepository: MovieRepository,
    val homeScreenMapper: HomeScreenMapper,
) : ViewModel() {

    private val _popularMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val popularMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _popularMoviesHomeViewState.asStateFlow()

    private val _nowPlayingMoviesHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val nowPlayingMoviesHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _nowPlayingMoviesHomeViewState.asStateFlow()

    private val _upcomingMovieHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val upcomingMovieHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _upcomingMovieHomeViewState.asStateFlow()

    init {
        getPopular(homeScreenMapper)
        getUpcoming(homeScreenMapper)
        getNowPlaying(homeScreenMapper)
    }

    fun getPopular(homeScreenMapper: HomeScreenMapper) {
        viewModelScope.launch {
            movieRepository.movies(MovieCategory.POPULAR_STREAMING).collect {
                _popularMoviesHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = popularCategory,
                    selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
                    movies = it
                )
            }
        }
    }

    fun getNowPlaying(homeScreenMapper: HomeScreenMapper) {
        viewModelScope.launch {
            //movieService.fetchNowPlayingMovies()
            movieRepository.movies(MovieCategory.NOW_PLAYING_MOVIES).collect {
                _nowPlayingMoviesHomeViewState.value =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = nowPlayingCategory,
                        selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
                        movies = it
                    )
            }
        }
    }

    fun getUpcoming(homeScreenMapper: HomeScreenMapper) {
        viewModelScope.launch {
            //movieService.fetchUpcomingMovies()
            movieRepository.movies(MovieCategory.UPCOMING_TODAY).collect {
                _upcomingMovieHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = upcomingCategory,
                    selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
                    movies = it
                )
            }
        }
    }

    fun newListPopular(labelId : Int){
        viewModelScope.launch {
            //movieService.fetchPopularMovies()
            movieRepository.movies(MovieCategory.POPULAR_STREAMING).collect {
                _popularMoviesHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = popularCategory,
                    selectedMovieCategory = MovieCategory.getByOrdinal(labelId)!!,
                    movies = it
                )
            }
        }
    }

    fun newListNowPlaying(labelId : Int){
        viewModelScope.launch {
            //movieService.fetchNowPlayingMovies()
            movieRepository.movies(MovieCategory.NOW_PLAYING_MOVIES).collect {
                _nowPlayingMoviesHomeViewState.value =
                    homeScreenMapper.toHomeMovieCategoryViewState(
                        movieCategories = nowPlayingCategory,
                        selectedMovieCategory = MovieCategory.getByOrdinal(labelId)!!,
                        movies = it
                    )
            }
        }
    }

    fun newListUpcoming(labelId : Int){
        viewModelScope.launch {
            //movieService.fetchUpcomingMovies()
            movieRepository.movies(MovieCategory.UPCOMING_TODAY).collect {
                _upcomingMovieHomeViewState.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = upcomingCategory,
                    selectedMovieCategory = MovieCategory.getByOrdinal(labelId)!!,
                    movies = it
                )
            }
        }
    }

    fun toggleFav(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
