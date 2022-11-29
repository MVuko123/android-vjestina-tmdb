package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class MovieDetailsViewModel (
    private val movieRepository: MovieRepository,
    movieDetailsMapper: MovieDetailsMapper,
// other parameters if needed
) : ViewModel() {
    // private mutable state flows if needed

    val movieDetailsViewState: StateFlow<MovieDetailsViewState> = TODO()// your implementation

    // other view states if needed

    /*
    fun someAction(...) {

    }
    */


    // other actions
}
