package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    val favoritesMapper: FavoritesMapper,
) : ViewModel() {

    private val _favoritesMovieViewState = MutableStateFlow(FavoritesViewState())
    val favoritesMovieViewState: StateFlow<FavoritesViewState> =
        _favoritesMovieViewState.asStateFlow()

    init {
        getFavorite()
    }

    private fun getFavorite() {
        viewModelScope.launch {
            movieRepository.favoriteMovies().collect { movie ->
                _favoritesMovieViewState.value = favoritesMapper.toFavoritesViewState(movie)
            }
        }
    }

    fun toggleFav(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
