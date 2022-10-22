package agency.five.codebase.android.movieapp.ui.component


import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import agency.five.codebase.android.movieapp.ui.theme.Favorite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier
) {


   var isFavorite by remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = if (isFavorite) R.drawable.heartfull else R.drawable.hearthoutline),
        contentDescription = null,
        modifier = modifier
            .clickable {
               isFavorite = !isFavorite
            }
            .size(60.dp)
            .background(Favorite, CircleShape)
            .padding(15.dp)
    )

}





