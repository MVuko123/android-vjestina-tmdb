package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
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
        AsyncImage(
            model = "${actorCardViewState.imageUrl}",
            contentDescription = null ,
            modifier = Modifier.width(100.dp)
        )
        Text(text = "${actorCardViewState.name}",
            modifier = Modifier.width(100.dp).padding(start = 5.dp, end = 2.dp, top = 3.dp),)
        Text(text = "${actorCardViewState.character}",
            modifier = Modifier.width(100.dp).alpha(ContentAlpha.disabled).padding(start = 5.dp, bottom = 5.dp, end = 2.dp, top = 2.dp),
            fontSize = 10.sp,
        )
    }



    @Preview
    @Composable
    private fun ActorCardPreview() {
        Surface(color = MaterialTheme.colors.background) {
            Card(modifier = Modifier.fillMaxSize()) {
                val paddingModifier = Modifier.padding(10.dp)
                Column(modifier = paddingModifier) {
                    ActorCard(actorCardViewState = ActorCardViewState(
                        "https://pinkmirror.com/getImagePerson.ashx?id=1&&photo_type=photo",
                        "Robert Downey Jr.",
                        "TonyStark/IronMan"))
                }
            }
        }
    }





