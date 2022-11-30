package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    val movieDetailsMapper: MovieDetailsMapper,
    val movieId: Int
) : ViewModel() {

    private val _movieDetailsViewState =
        MutableStateFlow(MovieDetailsViewState())//(1, "", 0.0f, "", "", false, listOf(), listOf()))
    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        _movieDetailsViewState.asStateFlow()

    init {
        getMovieDetails(movieId)
    }

     fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.movieDetails(movieId).collect {
                _movieDetailsViewState.value = movieDetailsMapper.toMovieDetailsViewState(it)
            }
        }
    }

    fun toggleFav(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

}

