package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

sealed class MovieCategoryLabelTextViewState()

class MovieCategoryString(val category: String): MovieCategoryLabelTextViewState()

class MovieCategoryStringRes(@StringRes val textRes: Int): MovieCategoryLabelTextViewState()

data class MovieCategoryLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    modifier: Modifier
) {

    Text(text = movieCategoryLabelViewState.categoryText.toString(),
        modifier = modifier
            .clickable {
                movieCategoryLabelViewState.isSelected =
                    movieCategoryLabelViewState.isSelected.not()
            },
        fontWeight = if (movieCategoryLabelViewState.isSelected) FontWeight.Bold
                     else FontWeight.Normal,
    )

}




