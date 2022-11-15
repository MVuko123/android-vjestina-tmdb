package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsAll
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategory = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_FOR_RENT,
    MovieCategory.POPULAR_IN_THEATRES
)

val nowPlayingCategory = listOf(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV
)

val upcomingCategory = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK
)

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(popularCategory, MovieCategory.POPULAR_STREAMING, MoviesMock.getMoviesList() )
val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(nowPlayingCategory, MovieCategory.NOW_PLAYING_MOVIES, MoviesMock.getMoviesList())
val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(upcomingCategory, MovieCategory.UPCOMING_TODAY, MoviesMock.getMoviesList())

/*
@Composable
fun HomeRoute(
) {
    val home by remember { mutableStateOf(xxxViewState) }
    HomeScreen(
        home,
    )
}
*/

@Composable
fun HomeScreen(
    popularCategory : HomeMovieCategoryViewState,
    nowPlayingCategory : HomeMovieCategoryViewState,
    upcomingCategory : HomeMovieCategoryViewState
) {

    LazyColumn{
        item {
           AllHomeScreen(
               popularCategory = popularCategory,
               nowPlayingCategory = nowPlayingCategory,
               upcomingCategory = upcomingCategory
           )
        }
    }

}

@Composable
fun AllHomeScreen(
    popularCategory : HomeMovieCategoryViewState,
    nowPlayingCategory : HomeMovieCategoryViewState,
    upcomingCategory : HomeMovieCategoryViewState,
){

    Segments(homeViewState = popularCategory, title = "What's popular")
    Segments(homeViewState = nowPlayingCategory, title = "Now Playing")
    Segments(homeViewState = upcomingCategory, title = "Upcoming")

}


@Composable
fun Segments(
    modifier: Modifier = Modifier,
    homeViewState: HomeMovieCategoryViewState,
    title : String
){
    Text(
        text = "${title}",
        color = MaterialTheme.colors.onSurface,
        modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.size(10.dp))
    LazyRow(
        modifier = Modifier.height(60.dp),
        content = {
            items(
                items = homeViewState.movieCategories,
                key = { categories -> categories.itemId }) { categories ->
                MovieCategoryLabel(
                    movieCategoryLabelViewState = categories
                )
            }
        }
    )
    LazyRow(
        modifier = Modifier.height(220.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(
                items = homeViewState.movies,
                key = { movies -> movies.id }) { movies ->
                MovieCard(
                    modifier = androidx.compose.ui.Modifier
                        .height(200.dp)
                        .width(150.dp),
                    movieCardViewState = MovieCardViewState(movies.imageUrl, movies.isFavorite),
                    onClick = {}
                )
            }
        }
    )
}



@Preview
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen(
            popularCategory = popularCategoryViewState,
            nowPlayingCategory = nowPlayingCategoryViewState,
            upcomingCategory = upcomingCategoryViewState
        )
    }
}
