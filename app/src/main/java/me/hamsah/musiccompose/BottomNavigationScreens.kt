package me.hamsah.musiccompose

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArtTrack
import androidx.compose.material.icons.rounded.Audiotrack
import androidx.compose.material.icons.rounded.Headset
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import me.hamsah.musiccompose.MainDestinations.ARTISTS_ROOT
import me.hamsah.musiccompose.MainDestinations.BROWSER_ROOT
import me.hamsah.musiccompose.MainDestinations.GENRES_ROOT
import me.hamsah.musiccompose.MainDestinations.HOME_ROOT
import me.hamsah.musiccompose.MainDestinations.MY_MUSIC_ROOT

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
) {

    object Browser :
        BottomNavigationScreens(BROWSER_ROOT, R.string.browser_screen_route, Icons.Rounded.ArtTrack)

    object Artists :
        BottomNavigationScreens(ARTISTS_ROOT, R.string.artists_screen_route, Icons.Rounded.Person)

    object Home :
        BottomNavigationScreens(HOME_ROOT, R.string.home_screen_route, Icons.Rounded.Home)

    object Genres :
        BottomNavigationScreens(GENRES_ROOT, R.string.genres_bag_screen_route, Icons.Rounded.Audiotrack)

    object MyMusic :
        BottomNavigationScreens(MY_MUSIC_ROOT, R.string.my_music_screen_route, Icons.Rounded.Headset)
}