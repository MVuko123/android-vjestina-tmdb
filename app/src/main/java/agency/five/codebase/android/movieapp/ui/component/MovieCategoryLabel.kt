package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed class MovieCategoryLabelTextViewState {
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
    onLabelClick: () -> Unit,
) {
    Column(modifier = modifier.padding(10.dp)) {
        var selected = movieCategoryLabelViewState.isSelected
        Column(
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Max)
                .clickable(onClick = onLabelClick)
        ) {
            Text(
                modifier = Modifier.clickable { selected = !selected },
                text = TextSource(movieCategoryLabelViewState = movieCategoryLabelViewState),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Light,
                color = MaterialTheme.colors.onSurface,
            )
            if (selected)
                Divider(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth(),
                    thickness = 5.dp,
                    color = Color.Gray
                )
        }
    }
}

@Composable
fun TextSource(movieCategoryLabelViewState: MovieCategoryLabelViewState): String {
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
        itemId = 2,
        isSelected = false,
        categoryText = MovieCategoryLabelTextViewState.MovieCategoryString("Movies")),
        modifier = Modifier.padding(5.dp),
        onLabelClick = {}
    )
}

