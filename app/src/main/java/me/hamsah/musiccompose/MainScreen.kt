package me.hamsah.musiccompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import me.hamsah.musiccompose.ui.theme.BGE
import me.hamsah.musiccompose.ui.theme.BGS
import me.hamsah.musiccompose.ui.theme.DarkBackground
import me.hamsah.musiccompose.ui.theme.Silver
import me.hamsah.musiccompose.ui.theme.TextGrey

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { AppBottomNavigation(navController) },
    ) {
        NavGraph(navController)
    }
}

@Composable
private fun AppBottomNavigation(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavigationScreens.Browser,
        BottomNavigationScreens.Artists,
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Genres,
        BottomNavigationScreens.MyMusic
    )

    BottomNavigation(
        modifier = Modifier.navigationBarsHeight(additional = 64.dp),
        backgroundColor = DarkBackground
    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            if (screen is BottomNavigationScreens.Home) {
                HomeBottomTab(
                    navController = navController,
                    screen = screen,
                    isSelected = currentRoute == screen.route
                )
            } else {
                BottomTab(
                    navController = navController,
                    screen = screen,
                    isSelected = currentRoute == screen.route
                )
            }
        }
    }
}

@Composable
fun HomeBottomTab(
    navController: NavHostController,
    screen: BottomNavigationScreens.Home,
    isSelected: Boolean
) {
    val backgroundColor = if (isSelected) {
        Brush.linearGradient(listOf(BGS, BGE))
    } else {
        SolidColor(TextGrey)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
            .aspectRatio(1f)
            .background(backgroundColor, shape = CircleShape)
            .clickable {
                navigate(navController, screen.route)
            }
    ) {
        Icon(
            imageVector = screen.icon,
            contentDescription = screen.route,
            tint = Silver
        )
    }
}

@Composable
fun RowScope.BottomTab(
    navController: NavHostController,
    screen: BottomNavigationScreens,
    isSelected: Boolean
) {
    BottomNavigationItem(
        icon = {
            Icon(screen.icon, screen.route)
        },
        label = {
            Text(
                text = stringResource(screen.resourceId),
                maxLines = 1
            )
        },
        selectedContentColor = Silver,
        unselectedContentColor = TextGrey,
        alwaysShowLabel = false,
        selected = isSelected,
        onClick = {
            navigate(navController, screen.route)
        },
        modifier = Modifier.navigationBarsPadding()
    )
}

fun navigate(navController: NavHostController, route: String) {
    navController.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview(group = "Main", name = "Bottom bar - animated")
@Composable
fun PreviewMainScreen() {
    MainScreen()
}