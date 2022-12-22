package com.sonasetiana.techtestkozokku.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.UserCard
import com.sonasetiana.techtestkozokku.presentation.modules.user.UserViewModel
import com.sonasetiana.techtestkozokku.presentation.navigation.NavigationItem
import com.sonasetiana.techtestkozokku.presentation.navigation.Screen
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

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
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.TimeLine.route,
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    BottomNavigation(modifier = modifier) {
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_user),
                icon = Icons.Default.Person,
                screen = Screen.User
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_timeline),
                icon = Icons.Default.List,
                screen = Screen.TimeLine
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            )
        )
        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = true,
                    onClick = {  }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListUser() {
    val viewModel = koinViewModel<UserViewModel>()
    val userItems = viewModel.userListState.collectAsLazyPagingItems()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        userItems.refresh()
    }
    val refreshState = rememberPullRefreshState(refreshing, ::refresh)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(Spacing.xNormal),
        modifier = Modifier.pullRefresh(refreshState)
    ) {
        if (!refreshing) {
            items(userItems.itemCount) { index ->
                userItems[index]?.let { user ->
                    UserCard(
                        fullName = "${user.title} ${user.firstName} ${user.lastName}",
                        picture = user.picture.orEmpty(),
                        modifier = Modifier.padding(Spacing.small)
                    )
                }
            }
        }
        /**
         * State when Paging initial load
         */
        when(userItems.loadState.refresh) {
            is LoadState.NotLoading -> {
                refreshing = false
            }
            is LoadState.Loading -> {
                refreshing = true
                items(10) {
                    ShimmerGridLoading()
                }
            }
            is LoadState.Error -> item {
                Text(text = "Error")
            }
        }

    }

}
