package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    isFavorite: Boolean = false,
    modifier: Modifier = Modifier,
    favoriteClick: () -> Unit,
) {
    Surface(
        modifier = modifier.padding(10.dp),
        shape = CircleShape,
        color = Color(0x77000000),
    ) {
        Image(
            modifier = Modifier
                .clickable { favoriteClick() }
                .size(35.dp)
                .padding(8.dp),
            painter = painterResource(id = if (isFavorite) R.drawable.heartfull else R.drawable.hearthoutline),
            contentDescription = null,
        )
    }
}

/*
@Preview
@Composable
private fun FavoriteButtonPreview() {
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    MovieAppTheme {
        FavoriteButton(isFavorite = isFavorite) {
            isFavorite = it
        }
    }
}
 */
