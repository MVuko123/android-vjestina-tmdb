package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier.padding(10.dp))
    {
        Card(shape = RoundedCornerShape(4.dp)) {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
            ) {
                AsyncImage(
                    model = "${actorCardViewState.imageUrl}",
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
                Spacer(modifier = Modifier.size(3.dp))
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 5.dp, end = 2.dp),
                    text = "${actorCardViewState.name}",
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(3.dp))
                Text(
                    modifier = Modifier
                        .width(100.dp)
                        .alpha(ContentAlpha.disabled)
                        .padding(start = 5.dp, bottom = 10.dp, end = 2.dp),
                    text = "${actorCardViewState.character}",
                    fontSize = 10.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    MovieAppTheme {
        ActorCard(actorCardViewState = ActorCardViewState(
            "https://pinkmirror.com/getImagePerson.ashx?id=1&&photo_type=photo",
            "Robert Downey Jr.",
            "TonyStark/IronMan"))
    }
}