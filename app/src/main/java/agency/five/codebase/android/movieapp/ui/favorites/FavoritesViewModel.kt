package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    favoritesMapper: FavoritesMapper,
// other parameters if needed
) : ViewModel() {
    // private mutable state flows if needed

    val favoritesMovieViewState: StateFlow<FavoritesMovieViewState> = TODO()// your implementation

    // other view states if needed

    /*
    fun someAction(...) {

    }
    */


    // other actions
}
