package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceType")
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


                        CrewCard(crewCardCardViewState = CrewCardCardViewState(
                            "Jon Favreau",
                            "Director"))

                        FavoriteButton()

                    }
                    Row {

                        MovieCard(movieCardViewState =
                        MovieCardViewState(
                            "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_FMjpg_UX1000_.jpg")
                        )

                        UserScoreProgressBar(percentage = 0.52f)


                    }
                    Row {
                        MovieCategoryLabel(movieCategoryLabelViewState = MovieCategoryLabelViewState(
                            2,
                            false,
                            MovieCategoryString("Movies")),
                            modifier = Modifier.padding(5.dp))
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
