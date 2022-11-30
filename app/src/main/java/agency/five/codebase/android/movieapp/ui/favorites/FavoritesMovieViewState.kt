package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState

data class FavoritesMovieViewState(
    val favoriteMovieId: Int,
    val movieCard: MovieCardViewState,
)

data class FavoritesViewState(
    val favoritesMovieViewState: List<FavoritesMovieViewState> = listOf(),
)
