package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

data class MovieCardViewState(
    val movieImageUrl: String?,
    val isFavorite: Boolean,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    toMovieDetails: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = modifier
            .size(200.dp, 300.dp)
            .padding(10.dp)
            .clip(shape)
            .clickable(onClick = toMovieDetails)
    ) {
        AsyncImage(
            model = movieCardViewState.movieImageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.error),
            error = painterResource(id = R.drawable.error)
        )
        FavoriteButton(
            modifier = Modifier,
            isFavorite = movieCardViewState.isFavorite,
            favoriteClick = { onFavoriteClick(movieCardViewState.isFavorite) }
        )
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    MovieAppTheme {
        val movieCard = MoviesMock.getMoviesList()[0]
        val viewCardState = MovieCardViewState(
            movieImageUrl = movieCard.imageUrl,
            isFavorite = movieCard.isFavorite
        )
        MovieCard(movieCardViewState = viewCardState, toMovieDetails = {}, onFavoriteClick = {})
    }
}
