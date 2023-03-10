package com.sonasetiana.techtestkozokku.presentation.modules.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.presentation.components.EmptyView
import com.sonasetiana.techtestkozokku.presentation.components.TimeLineCard
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.utils.toast
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<FavoriteViewModel>()

    val favoriteItems = viewModel.favorites.collectAsLazyPagingItems()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (favoriteItems.itemCount > 0) {
            FavoriteListView(
                items = favoriteItems,
                onLikeClick = { post ->
                    viewModel.deleteFavorite(post)
                    favoriteItems.refresh()
                    context.toast("Success delete from favorite")
                }
            )
        } else {
            EmptyView()
        }
    }


}

@Composable
fun FavoriteListView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<UserPostResponse>,
    onLikeClick: ((UserPostResponse) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier.testTag("FavoriteList")
    ) {
        items(items) { items ->
            items?.let { timeLine ->
                TimeLineCard(
                    item = timeLine,
                    modifier = Modifier.padding(bottom = Spacing.normal),
                    isLiked = true,
                    onLikeClick = onLikeClick
                )
            }
        }
    }
}