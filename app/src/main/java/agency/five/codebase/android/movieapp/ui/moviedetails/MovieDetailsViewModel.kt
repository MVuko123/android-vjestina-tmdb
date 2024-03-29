package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    val movieDetailsMapper: MovieDetailsMapper,
    val movieId: Int
) : ViewModel() {

    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        movieRepository.movieDetails(movieId)
            .map { movies -> movieDetailsMapper.toMovieDetailsViewState(movies) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(1000L),
                MovieDetailsViewState(
                    id = 0,
                    imageUrl = "temp",
                    voteAverage = 9.5F,
                    title = "",
                    overview = "",
                    isFavorite = false,
                    crew = listOf(),
                    cast = listOf()
                )
            )

    fun toggleFav(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
