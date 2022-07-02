package me.hamsah.musiccompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.hamsah.musiccompose.screens.ArtistsScreen
import me.hamsah.musiccompose.screens.BrowserScreen
import me.hamsah.musiccompose.screens.GenresScreen
import me.hamsah.musiccompose.screens.HomeScreen
import me.hamsah.musiccompose.screens.MyMusicScreen

object MainDestinations {

    const val BROWSER_ROOT = "Browser"
    const val ARTISTS_ROOT = "Artists"
    const val HOME_ROOT = "Home"
    const val GENRES_ROOT = "Genres"
    const val MY_MUSIC_ROOT = "MyMusic"
}

@Composable
internal fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = BottomNavigationScreens.Browser.route
    ) {
        composable(BottomNavigationScreens.Browser.route) {
            BrowserScreen()
        }
        composable(BottomNavigationScreens.Artists.route) {
            ArtistsScreen()
        }
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen()
        }
        composable(BottomNavigationScreens.Genres.route) {
            GenresScreen()
        }
        composable(BottomNavigationScreens.MyMusic.route) {
            MyMusicScreen()
        }
    }
}