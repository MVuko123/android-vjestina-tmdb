package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

sealed class MovieCategoryLabelTextViewState()

class MovieCategoryString(val category: String): MovieCategoryLabelTextViewState(){}

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
    Column(modifier = modifier.padding(10.dp)) {
        //var selected = movieCategoryLabelViewState.isSelected
        var selected by remember { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column() {
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
                        .padding(top = 7.dp, bottom = 7.dp)
                        .width(100.dp), thickness = 5.dp)

            }

        }
    }
}











