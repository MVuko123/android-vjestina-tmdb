package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import android.annotation.SuppressLint
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

sealed class MovieCategoryLabelTextViewState()

class MovieCategoryString(val category: String) : MovieCategoryLabelTextViewState() {}

class MovieCategoryStringRes(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()

data class MovieCategoryLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState,
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier,
) {
    Column(modifier = modifier.padding(10.dp)) {
        //var selected = movieCategoryLabelViewState.isSelected
        var selected by remember { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.padding(3.dp)
        ) {
            Column(modifier = modifier.width(intrinsicSize = IntrinsicSize.Max)) {
                Text(
                    stringResource(id = R.string.app_name),
                    fontWeight = if (selected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Light
                    },
                    modifier = modifier
                        .clickable {
                            selected = !selected
                        }

                )
                if (selected)
                    Divider(modifier = modifier
                        .padding( bottom = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth(), thickness = 5.dp, color = Color.Gray)

            }

        }
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview() {
    MovieCategoryLabel(movieCategoryLabelViewState = MovieCategoryLabelViewState(
        2,
        false,
        MovieCategoryString("Movies")),
        modifier = Modifier.padding(5.dp))
}
