package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class CrewCardViewState(
    val name: String,
    val role: String,
)

@Composable
fun CrewCard(
    crewCardCardViewState: CrewCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            Text(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 5.dp,
                        top = 3.dp,
                        bottom = 3.dp
                    ),
                text = crewCardCardViewState.name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp,
                        end = 5.dp,
                        top = 3.dp,
                        bottom = 5.dp
                    ),
                text = crewCardCardViewState.role,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}

@Preview
@Composable
private fun CrewCardPreview() {
    MovieAppTheme {
        CrewCard(crewCardCardViewState = CrewCardViewState(
            "Jon Favreau",
            "Director"))
    }
}
