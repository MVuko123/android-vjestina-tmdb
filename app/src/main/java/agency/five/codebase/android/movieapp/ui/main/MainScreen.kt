package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.navigation.MOVIE_ID_KEY
import agency.five.codebase.android.movieapp.navigation.NavigationItem
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesRoute
import agency.five.codebase.android.movieapp.ui.home.HomeRoute
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsRoute
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var showBottomBar by remember {
        mutableStateOf(true)
    }
    val showBackIcon = !showBottomBar
    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(
                        onBackClick = {
                            showBottomBar = showBottomBar.not()
                            navController.popBackStack()
                        }
                    )
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ),
                    onNavigateToDestination = {
                        navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            NavHost(
                modifier = Modifier.padding(padding),
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        onNavigateToMovieDetails = {
                            showBottomBar = showBottomBar.not()
                            navController.navigate(it)
                        }
                    )
                }
                composable(NavigationItem.FavoritesDestination.route) {
                    FavoritesRoute(
                        onNavigateToMovieDetails = {
                            showBottomBar = showBottomBar.not()
                            navController.navigate(it)
                        }
                    )
                }
                composable(
                    route = NavigationItem.MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType }),
                ) {
                    MovieDetailsRoute()
                }
            }
        }
    }
}

@Composable
private fun TopBar(navigationIcon: @Composable (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.tmdb))
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.tmdb_logo), contentDescription = null)
        if (navigationIcon != null) {
            navigationIcon()
        }
    }
}

@Composable
private fun BackIcon(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = onBackClick),
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null,
        )
    }
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (NavigationItem) -> Unit,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        destinations.forEach { destination ->
            BottomNavigationItem(
                currentDestination?.route == destination.route,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Image(painter = painterResource(id =
                        if (currentDestination?.route == destination.route)
                            destination.selectedIconId
                        else
                            destination.unselectedIconId),
                            contentDescription =
                            if (currentDestination?.route == destination.route)
                                stringResource(id = R.string.home)
                            else
                                stringResource(id = R.string.favorites),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
                        )
                        Text(text = stringResource(id = destination.labelId))
                    }
                },
                onClick = { onNavigateToDestination(destination) }
            )
        }
    }
}
