package com.sonasetiana.techtestkozokku.presentation.modules.favorite

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.TimeLineCard
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<FavoriteViewModel>()

    val favoriteItems = viewModel.favorites.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {
        items(favoriteItems) { items ->
            items?.let { timeLine ->
                TimeLineCard(
                    item = timeLine,
                    modifier = Modifier.padding(bottom = Spacing.medium),
                    isLiked = true,
                    onLikeClick = { post ->
                        viewModel.deleteFavorite(post)
                        favoriteItems.refresh()
                    }
                )
            }
        }

        /**
         * State when Paging initial load
         */
        when(favoriteItems.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
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