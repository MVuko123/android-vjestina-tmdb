package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel (
    private val movieRepository: MovieRepository,
    homeScreenMapper: HomeScreenMapper,
// other parameters if needed
) : ViewModel() {
    // private mutable state flows if needed

    val homeViewState: StateFlow<HomeMovieCategoryViewState> = TODO()// your implementation

    // other view states if needed

    /*
    fun someAction(...) {

    }
    */


    // other actions
}
