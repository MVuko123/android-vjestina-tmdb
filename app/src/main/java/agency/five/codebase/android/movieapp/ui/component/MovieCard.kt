package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview

data class MovieCardViewState(
    val movieImageUrl: String?,
    val isFavorite: MutableState<Boolean>,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = modifier
            .size(200.dp, 300.dp)
            .padding(10.dp)
            .clip(shape)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = "${movieCardViewState.movieImageUrl}",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        FavoriteButton(isFavorite = movieCardViewState.isFavorite.value) {
            movieCardViewState.isFavorite.value = it
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    MovieAppTheme {
        val movieCard = MoviesMock.getMoviesList()[0]
        val viewCardState = MovieCardViewState(
            movieImageUrl = movieCard.imageUrl,
            isFavorite = rememberSaveable {
                mutableStateOf(movieCard.isFavorite)
            }
        )
        MovieCard(movieCardViewState = viewCardState, onClick = {})
    }
}
