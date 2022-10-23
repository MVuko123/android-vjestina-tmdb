package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                //Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                //Greeting("Android")
                //}
                Column {
                    Row {
                        ActorCard(actorCardViewState = ActorCardViewState(
                            "https://pinkmirror.com/getImagePerson.ashx?id=1&&photo_type=photo",
                            "Robert Downey Jr.",
                            "TonyStark/IronMan"))


                        CrewmanCard(crewmanCardCardViewState = CrewmanCardCardViewState(
                            "Jon Favreau",
                            "Director"))

                        FavoriteButton()

                    }
                    Row {

                        MovieCard(movieCardViewState =
                        MovieCardViewState("https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_FMjpg_UX1000_.jpg")
                        )

                        UserScoreProgressBar(percentage = 0.6f)


                    }

                }


            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}
