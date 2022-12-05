package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.LightGreen
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.annotation.FloatRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun UserScoreProgressBar(
    @FloatRange(from = 0.0, to = 1.0) percentage: Float,
    modifier: Modifier = Modifier,
) {
    val radius = 80.dp
    val animationDuration = 1000
    var animFinished by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animFinished) percentage else 0f,
        animationSpec = tween(
            //built it animation in witch we determine the lenght, wait time ...
            durationMillis = animationDuration,
        )
    )
    LaunchedEffect(key1 = true) {
        animFinished = true
    }
    Box(
        modifier = modifier.size(45.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(radius)) {
            drawArc(
                color = LightGreen,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    5.dp.toPx(),
                    cap = StrokeCap.Round,
                ),
            )
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360 * currentPercent.value,
                useCenter = false,
                style = Stroke(
                    5.dp.toPx(),
                    cap = StrokeCap.Round,
                ),
            )
        }
        Text(
            text = "${percentage * 10}",
            color = Color.White,
            fontSize = TextUnit.Unspecified
        )
    }
}

@Preview
@Composable
private fun UserScorePRogressBarPreview() {
    MovieAppTheme {
        UserScoreProgressBar(percentage = 0.52f)
    }
}
