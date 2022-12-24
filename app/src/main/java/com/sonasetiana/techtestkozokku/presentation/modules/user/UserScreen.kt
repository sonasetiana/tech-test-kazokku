package com.sonasetiana.techtestkozokku.presentation.modules.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sonasetiana.techtestkozokku.data.model.UserResponse
import com.sonasetiana.techtestkozokku.presentation.components.ErrorView
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.UserCard
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    openDetail: ((String) -> Unit) ? = null
) {
    val viewModel = koinViewModel<UserViewModel>()
    val userItems = viewModel.userListState.collectAsLazyPagingItems()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        userItems.refresh()
    }
    val refreshState = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        UserListView(
            items = userItems,
            isRefreshing = refreshing,
            modifier = Modifier.pullRefresh(refreshState),
            onItemClick = { userId ->
                openDetail?.invoke(userId)
            },
            onRefreshing = { isRefresh ->
                refreshing = isRefresh
            }
        )


        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))

        /**
         * State when Paging initial load
         */

        if (userItems.loadState.refresh is LoadState.Error) {
            val message = (userItems.loadState.refresh as LoadState.Error).error.message
            ErrorView(
                message = message,
                modifier = Modifier.align(Alignment.Center)
            ) {
                userItems.refresh()
            }
        }

    }
}

@Composable
fun UserListView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<UserResponse>,
    isRefreshing: Boolean,
    onItemClick: ((String) -> Unit)? = null,
    onRefreshing: ((Boolean) -> Unit)? = null
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(Spacing.small),
        modifier = modifier.testTag("UserList")
    ) {
        if (!isRefreshing) {
            items(items.itemCount) { index ->
                items[index]?.let { user ->
                    UserCard(
                        fullName = user.fullName,
                        picture = user.picture.orEmpty(),
                        modifier = Modifier
                            .padding(Spacing.small)
                            .clickable {
                                onItemClick?.invoke(user.id.orEmpty())
                            }
                    )
                }
            }
        }
        /**
         * State when Paging initial load
         */
        when(items.loadState.refresh) {
            is LoadState.NotLoading -> onRefreshing?.invoke(false)
            is LoadState.Loading -> {
                onRefreshing?.invoke(true)
                items(10) { index ->
                    ShimmerGridLoading(
                        modifier = Modifier.testTag("ShimmerGridLoading$index")
                    )
                }
            }
            is LoadState.Error -> Unit
        }

    }
}