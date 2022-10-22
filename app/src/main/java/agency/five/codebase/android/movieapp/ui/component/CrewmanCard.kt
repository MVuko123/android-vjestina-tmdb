package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class CrewmanCardCardViewState(
    val name: String,
    val role: String,
)

@Composable
fun CrewmanCard(
    crewmanCardCardViewState: CrewmanCardCardViewState,
    modifier: Modifier = Modifier,
) {
    Text( text = "${crewmanCardCardViewState.name}",
          modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 3.dp, bottom = 3.dp),
          fontWeight = FontWeight.Bold
    )

    Text(text = "${crewmanCardCardViewState.role}",
    modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 3.dp, bottom = 5.dp), )
}

@Preview
@Composable
private fun CrewmanCardPreview(){
    MovieAppTheme {
        Card {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(4.dp)),
            ) {
                CrewmanCard(crewmanCardCardViewState = CrewmanCardCardViewState(
                    "Jon Favreau",
                    "Director"))
            }
        }
    }
}