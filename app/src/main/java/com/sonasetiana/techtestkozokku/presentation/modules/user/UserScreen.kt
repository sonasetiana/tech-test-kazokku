package com.sonasetiana.techtestkozokku.presentation.modules.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(Spacing.xNormal),
        modifier = modifier.pullRefresh(refreshState)
    ) {
        if (!refreshing) {
            items(userItems.itemCount) { index ->
                userItems[index]?.let { user ->
                    UserCard(
                        fullName = "${user.title} ${user.firstName} ${user.lastName}",
                        picture = user.picture.orEmpty(),
                        modifier = Modifier.padding(Spacing.small).clickable {
                            openDetail?.invoke(user.id.orEmpty())
                        }
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