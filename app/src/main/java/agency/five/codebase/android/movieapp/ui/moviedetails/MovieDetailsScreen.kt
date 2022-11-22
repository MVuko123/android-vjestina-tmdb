package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
    modifier: Modifier = Modifier,
) {
    val details by remember { mutableStateOf(movieDetailsViewState) }
    MovieDetailsScreen(
        details,
        modifier = modifier.padding(10.dp),
    )
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        MovieDetailsAll(
            modifier = modifier,
            movieDetailsViewState = movieDetailsViewState,
        )
    }
}

@Composable
fun MovieDetailsAll(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    MovieDetailsBanner(movieDetailsViewState = movieDetailsViewState)
    Spacer(modifier = modifier.size(10.dp))
    MovieDetailsOverview(movieDetailsViewState = movieDetailsViewState)
    Spacer(modifier = Modifier.size(10.dp))
    MovieDetailsCast(movieDetailsViewState = movieDetailsViewState)
}

@Composable
fun MovieDetailsBanner(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = movieDetailsViewState.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    modifier = Modifier.padding(start = 10.dp),
                    percentage = movieDetailsViewState.voteAverage)
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "User Score",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = movieDetailsViewState.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(10.dp))
            val onFavoriteClick = !movieDetailsViewState.isFavorite
            FavoriteButton(isFavorite = movieDetailsViewState.isFavorite) {
                onFavoriteClick
            }
        }
    }
}

@Composable
fun MovieDetailsOverview(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            text = "Overview",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            text = movieDetailsViewState.overview,
            color = MaterialTheme.colors.onSurface,
        )
        Spacer(modifier = Modifier.size(5.dp))
        LazyVerticalGrid(
            modifier = Modifier.height(165.dp),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            )
        ) {
            items(
                items = movieDetailsViewState.crew,
                key = { crew -> crew.id }) { crew ->
                CrewCard(crewCardCardViewState =
                CrewCardViewState(
                    crew.name,
                    crew.role
                )
                )
            }
        }
    }
}

@Composable
fun MovieDetailsCast(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            color = MaterialTheme.colors.onSurface,
            text = "Top Billed Cast",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(10.dp))
        LazyRow(
            modifier = Modifier.height(270.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            )
        ) {
            items(
                items = movieDetailsViewState.cast,
                key = { cast -> cast.id }
            ) { cast ->
                ActorCard(
                    actorCardViewState =
                    ActorCardViewState(
                        cast.imageUrl,
                        cast.name,
                        cast.character
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        MovieDetailsScreen(
            movieDetailsViewState = movieDetailsViewState
        )
    }
}
