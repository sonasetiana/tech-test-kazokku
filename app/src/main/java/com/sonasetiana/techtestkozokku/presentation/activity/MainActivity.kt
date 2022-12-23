package com.sonasetiana.techtestkozokku.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.modules.detail.DetailScreen
import com.sonasetiana.techtestkozokku.presentation.modules.favorite.FavoriteScreen
import com.sonasetiana.techtestkozokku.presentation.modules.timeline.TimeLineScreen
import com.sonasetiana.techtestkozokku.presentation.modules.user.UserScreen
import com.sonasetiana.techtestkozokku.presentation.navigation.NavigationItem
import com.sonasetiana.techtestkozokku.presentation.navigation.Screen
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechTestKozokkuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var currentRoute by rememberSaveable {
        mutableStateOf("Post")
    }
    var toolbarTitle by rememberSaveable {
        mutableStateOf("")
    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute = destination.route.orEmpty()
        toolbarTitle = when(currentRoute) {
            Screen.User.route -> "Users"
            Screen.Post.route -> "Posts"
            Screen.Favorite.route -> "Your Like"
            else -> toolbarTitle
        }

    }
    Scaffold(
        modifier = modifier,
        topBar = {
            if (currentRoute != Screen.Detail.route) {
                TopAppBar(
                    title = {
                        Text(
                            text = toolbarTitle,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colors.primary
                        )
                    },
                    backgroundColor = Color.White,
                    contentColor = Color.Gray,
                    elevation = Spacing.xSmall
                )
            }
        },
        bottomBar = {
            if (currentRoute!= Screen.Detail.route) {
                BottomBar(
                    navController = navController
                )
            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Post.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.User.route) {
                UserScreen(
                    openDetail = { userId ->
                        navController.navigate(Screen.Detail.createRoute(userId))
                    }
                )
            }
            composable(route = Screen.Post.route) {
                TimeLineScreen()
            }
            composable(route = Screen.Favorite.route) {
                FavoriteScreen()
            }
            composable(route = Screen.Detail.route,
                arguments = listOf(navArgument("id"){type = NavType.StringType})
            ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailScreen(userId = id, navController = navController)
            }
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_person),
                screen = Screen.User
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_new),
                screen = Screen.Post
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_favorite),
                screen = Screen.Favorite
            )
        )
        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = null
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
