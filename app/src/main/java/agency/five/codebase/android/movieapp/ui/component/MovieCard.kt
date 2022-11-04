package agency.five.codebase.android.movieapp.ui.component

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
import androidx.compose.ui.tooling.preview.Preview

data class MovieCardViewState(
    val movieImageUrl: String,
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = modifier
            .size(200.dp, 300.dp)
            .padding(10.dp)
            .clip(shape)
            .clickable {

            }
    ) {
        AsyncImage(
            model = "${movieCardViewState.movieImageUrl}",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        FavoriteButton()
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    MovieAppTheme {
        MovieCard(movieCardViewState =
        MovieCardViewState(
            "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_FMjpg_UX1000_.jpg")
        )
    }
}
