package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesMovieViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

/*
@Composable
fun FavoritesRoute(
) {
    val favorite by remember { mutableStateOf(favoritesViewState) }
    FavoriteScreen(
        favorite,
        modifier = Modifier.padding(10.dp)
    )
} */

@Composable
fun FavoriteScreen(
    favoritesViewState: FavoritesViewState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            header {
                Text(
                    color = MaterialTheme.colors.onSurface,
                    text = "Favorites",
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            items(
                items = favoritesViewState.favoritesMovieViewState,
                key = { movie -> movie.favoriteMovieId }) { card ->
                MovieCard(
                    modifier = Modifier
                        .height(180.dp)
                        .width(150.dp),
                    movieCardViewState = card.movieCard,
                    onClick = {}
                )
            }
        }
    )
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
        FavoriteScreen(favoritesViewState = favoritesMovieViewState)
    }
}


