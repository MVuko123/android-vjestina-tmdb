package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import androidx.compose.runtime.MutableState

data class HomeMovieViewState(
    val id: Int,
    val isFavorite: Boolean,
    val imageUrl: String?,
)

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState> = listOf(),
    val movies: List<HomeMovieViewState> = listOf(),
)
