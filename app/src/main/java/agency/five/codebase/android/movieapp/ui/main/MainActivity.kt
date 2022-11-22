package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        MainScreen()
    }
}
