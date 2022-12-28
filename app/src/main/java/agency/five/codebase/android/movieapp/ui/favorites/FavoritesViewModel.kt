package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    val favoritesMapper: FavoritesMapper,
) : ViewModel() {

    private val _favoritesMovieViewState = MutableStateFlow(FavoritesViewState())
    val favoritesMovieViewState: StateFlow<FavoritesViewState> =
        movieRepository.favoriteMovies()
            .map { movies -> favoritesMapper.toFavoritesViewState(movies) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, _favoritesMovieViewState.value)

    fun toggleFav(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
