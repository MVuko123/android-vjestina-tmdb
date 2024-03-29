package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.navigation.NavigationItem
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

val popularCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(popularCategory,
        MovieCategory.POPULAR_STREAMING,
        MoviesMock.getMoviesList())
val nowPlayingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(nowPlayingCategory,
        MovieCategory.NOW_PLAYING_MOVIES,
        MoviesMock.getMoviesList())
val upcomingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(upcomingCategory,
        MovieCategory.UPCOMING_TODAY,
        MoviesMock.getMoviesList())


@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    onNavigateToMovieDetails: (String) -> Unit,
) {
    val popularCategory: HomeMovieCategoryViewState by homeViewModel.popularMoviesHomeViewState.collectAsState()
    val nowPlayingCategory: HomeMovieCategoryViewState by homeViewModel.nowPlayingMoviesHomeViewState.collectAsState()
    val upcomingCategory: HomeMovieCategoryViewState by homeViewModel.upcomingMovieHomeViewState.collectAsState()

    HomeScreen(
        popularCategory = popularCategory,
        nowPlayingCategory = nowPlayingCategory,
        upcomingCategory = upcomingCategory,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteClick = { homeViewModel.toggleFav(it) },
        onPopularClick = { homeViewModel.switchSelectedCategory(it) },
        onNowPlayingClick = { homeViewModel.switchSelectedCategory(it) },
        onUpcomingClick = { homeViewModel.switchSelectedCategory(it) }
    )
}

@Composable
fun HomeScreen(
    popularCategory: HomeMovieCategoryViewState,
    nowPlayingCategory: HomeMovieCategoryViewState,
    upcomingCategory: HomeMovieCategoryViewState,
    onNavigateToMovieDetails: (String) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onPopularClick: (Int) -> Unit,
    onNowPlayingClick: (Int) -> Unit,
    onUpcomingClick: (Int) -> Unit
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        AllHomeScreen(
            popularCategory = popularCategory,
            nowPlayingCategory = nowPlayingCategory,
            upcomingCategory = upcomingCategory,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteClick = onFavoriteClick,
            onPopularClick = onPopularClick,
            onNowPlayingClick = onNowPlayingClick,
            onUpcomingClick = onUpcomingClick
        )
    }
}

@Composable
fun AllHomeScreen(
    popularCategory: HomeMovieCategoryViewState,
    nowPlayingCategory: HomeMovieCategoryViewState,
    upcomingCategory: HomeMovieCategoryViewState,
    onNavigateToMovieDetails: (String) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onPopularClick: (Int) -> Unit,
    onNowPlayingClick: (Int) -> Unit,
    onUpcomingClick: (Int) -> Unit
) {
    Segment(
        homeViewState = popularCategory,
        title = "What's popular",
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteClick = onFavoriteClick,
        onLabelClick = onPopularClick
    )
    Segment(
        homeViewState = nowPlayingCategory,
        title = "Now Playing",
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteClick = onFavoriteClick,
        onLabelClick = onNowPlayingClick
    )
    Segment(
        homeViewState = upcomingCategory,
        title = "Upcoming",
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onFavoriteClick = onFavoriteClick,
        onLabelClick = onUpcomingClick
    )
}

@Composable
fun Segment(
    homeViewState: HomeMovieCategoryViewState,
    title: String,
    modifier: Modifier = Modifier,
    onNavigateToMovieDetails: (String) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    onLabelClick: (Int) -> Unit,
) {
    Text(
        modifier = modifier.padding(start = 20.dp, top = 10.dp),
        text = title,
        color = MaterialTheme.colors.onSurface,
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        modifier = Modifier.height(50.dp),
        contentPadding = PaddingValues(start = 10.dp)
    ) {
        items(
            items = homeViewState.movieCategories,
            key = { categories -> categories.itemId }
        ) { categories ->

            MovieCategoryLabel(
                movieCategoryLabelViewState = MovieCategoryLabelViewState(
                    categories.itemId,
                    categories.isSelected,
                    categories.categoryText
                ),
                onLabelClick = { onLabelClick(categories.itemId) }
            )
        }
    }
    LazyRow(
        modifier = Modifier.height(210.dp),
        contentPadding = PaddingValues(
            start = 10.dp,
            end = 12.dp,
        )
    ) {
        items(
            items = homeViewState.movies,
            key = { movies -> movies.id }
        ) { movies ->
            MovieCard(
                modifier = androidx.compose.ui.Modifier
                    .height(200.dp)
                    .width(150.dp),
                movieCardViewState = MovieCardViewState(movies.imageUrl, movies.isFavorite),
                toMovieDetails = {
                    onNavigateToMovieDetails(NavigationItem.MovieDetailsDestination.createNavigationRoute(
                        movies.id))
                },
                onFavoriteClick = { onFavoriteClick(movies.id) }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen(
            popularCategory = popularCategoryViewState,
            nowPlayingCategory = nowPlayingCategoryViewState,
            upcomingCategory = upcomingCategoryViewState,
            onNavigateToMovieDetails = { it },
            onFavoriteClick = {},
            onPopularClick = {},
            onNowPlayingClick = {},
            onUpcomingClick = {}
        )
    }
}
