package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import agency.five.codebase.android.movieapp.ui.theme.Favorite
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onClick: (Boolean) -> Unit,
) {

    Surface(
        shape = CircleShape,
        color = Color(0x77000000),
        modifier = modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = if (isFavorite) R.drawable.heartfull else R.drawable.hearthoutline),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClick(isFavorite.not())
                }
                .size(35.dp)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    var isFavorite by remember { mutableStateOf(false) }
    MovieAppTheme {
        FavoriteButton(isFavorite = isFavorite) {
            isFavorite = it
        }
    }
}
