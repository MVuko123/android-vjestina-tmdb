package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.navigation.NavigationItem
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesMovieViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    modifier: Modifier = Modifier,
    onNavigateToMovieDetails: (String) -> Unit,
) {
    val favoriteState by remember { mutableStateOf(favoritesMovieViewState) }
    FavoriteScreen(
        modifier = modifier.padding(10.dp),
        favoritesViewState = favoriteState,
        onNavigateToMovieDetails = onNavigateToMovieDetails
    )
}

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoritesViewState: FavoritesViewState,
    onNavigateToMovieDetails: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            bottom = 16.dp
        )
    ) {
        header {
            Text(
                color = MaterialTheme.colors.onSurface,
                text = "Favorites",
                modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                fontWeight = FontWeight.Bold
            )
        }
        items(
            items = favoritesViewState.favoritesMovieViewState,
            key = { movie -> movie.favoriteMovieId }
        ) { movie ->
            MovieCard(
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp),
                movieCardViewState = movie.movieCard,
                toMovieDetails = {
                    onNavigateToMovieDetails(NavigationItem.MovieDetailsDestination.createNavigationRoute(
                        movie.favoriteMovieId))
                },
                onFavoriteClick = {}
            )
        }
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit,
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    MovieAppTheme {
        FavoriteScreen(favoritesViewState = favoritesMovieViewState, modifier = Modifier) {}
    }
}
