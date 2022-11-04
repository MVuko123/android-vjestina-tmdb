package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun UserScoreProgressBar(
    percentage: Float,
    radius: Dp = 80.dp,
    animationDuration: Int = 1000,
    modifier: Modifier = Modifier,
) {
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
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radius * 2)
    ) {
        Canvas(modifier = Modifier.size(radius)) {
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360 * currentPercent.value,
                useCenter = false,
                style = Stroke(
                    10.dp.toPx(),
                    cap = StrokeCap.Round,
                ),
            )
        }
        Surface(
            color = MaterialTheme.colors.background,
        ) {
            Text(text = "${percentage * 10}")
        }
    }
}

@Preview
@Composable
private fun UserScorePRogressBarPreview() {
    MovieAppTheme {
        UserScoreProgressBar(percentage = 0.52f)
    }
}
