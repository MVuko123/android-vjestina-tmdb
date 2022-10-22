package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                //Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                //Greeting("Android")
                //}
                Row {
                    Surface(color = MaterialTheme.colors.background,
                        modifier = Modifier.padding(10.dp)) {
                        Card {
                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(4.dp)),
                            ) {
                                ActorCard(actorCardViewState = ActorCardViewState(
                                    "https://pinkmirror.com/getImagePerson.ashx?id=1&&photo_type=photo",
                                    "Robert Downey Jr.",
                                    "TonyStark/IronMan"))
                            }
                        }
                    }
                    Surface(color = MaterialTheme.colors.background,
                        modifier = Modifier.padding(10.dp)) {
                        Card {
                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(4.dp)),
                            ) {
                                CrewmanCard(crewmanCardCardViewState = CrewmanCardCardViewState(
                                    "Jon Favreau",
                                    "Director"))
                            }
                        }
                    }

                    FavoriteButton(modifier = Modifier.padding(10.dp))

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
