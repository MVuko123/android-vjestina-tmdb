package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed class MovieCategoryLabelTextViewState{
    class MovieCategoryString(val category: String) : MovieCategoryLabelTextViewState()
    class MovieCategoryStringRes(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

data class MovieCategoryLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState,
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(10.dp)) {
        //var selected = movieCategoryLabelViewState.isSelected
        var selected by remember { mutableStateOf(false) }
            Column(modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max)) {
                Text(
                    textSource(movieCategoryLabelViewState = movieCategoryLabelViewState),
                    fontWeight = if (selected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Light
                    },
                    modifier = Modifier
                        .clickable {
                            selected = !selected
                        },
                    color = MaterialTheme.colors.onSurface,
                )
                if (selected)
                    Divider(modifier = Modifier
                        .padding(bottom = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth(), thickness = 5.dp, color = Color.Gray)
            }
        }
    }

@Composable
fun textSource(movieCategoryLabelViewState: MovieCategoryLabelViewState): String {
    val text = movieCategoryLabelViewState.categoryText
    return when (text) {
        is MovieCategoryLabelTextViewState.MovieCategoryString -> text.category
        is MovieCategoryLabelTextViewState.MovieCategoryStringRes -> stringResource(id = text.textRes)
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview() {
    MovieCategoryLabel(movieCategoryLabelViewState = MovieCategoryLabelViewState(
        2,
        false,
        MovieCategoryLabelTextViewState.MovieCategoryString("Movies")),
        modifier = Modifier.padding(5.dp))
}
