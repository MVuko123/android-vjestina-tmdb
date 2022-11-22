package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState
import androidx.compose.runtime.mutableStateOf

class FavoritesMapperImpl() : FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState =
        FavoritesViewState(
            favoriteMovies.map { movie ->
                FavoritesMovieViewState(
                    favoriteMovieId = movie.id,
                    movieCard = MovieCardViewState(
                        movieImageUrl = movie.imageUrl,
                        isFavorite = movie.isFavorite
                    )
                )
            }
        )
}
