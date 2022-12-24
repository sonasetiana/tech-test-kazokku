package com.sonasetiana.techtestkozokku.presentation.modules.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.presentation.components.ErrorView
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.TagFilterView
import com.sonasetiana.techtestkozokku.presentation.components.TimeLineCard
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.utils.toast
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimeLineScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<TimeLineViewModel>()

    var selectedTag by rememberSaveable {
        mutableStateOf("")
    }

    val timeLineItems = viewModel.timeLines.collectAsLazyPagingItems()

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        timeLineItems.refresh()
    }
    val refreshState = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        TimeLineContent(
            modifier = Modifier.pullRefresh(refreshState).testTag("PostList"),
            items = timeLineItems,
            isRefreshing = refreshing,
            selectedTag = selectedTag,
            viewModel = viewModel,
            onTagClick = { tagName ->
                selectedTag = tagName
                viewModel.setTagName(selectedTag)
                timeLineItems.refresh()
            },
            onRemoveTagClick = {
                selectedTag = ""
                viewModel.setTagName(selectedTag)
                timeLineItems.refresh()
            },
            onRefreshing = { isRefresh ->
                refreshing = isRefresh
            }
        )


        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))

        /**
         * State when Paging initial load
         */

        if (timeLineItems.loadState.refresh is LoadState.Error) {
            val message = (timeLineItems.loadState.refresh as LoadState.Error).error.message
            ErrorView(
                message = message,
                modifier = Modifier.align(Alignment.Center)
            ) {
                timeLineItems.refresh()
            }
        }
    }

}

@Composable
fun TimeLineContent(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<UserPostResponse>,
    isRefreshing: Boolean,
    selectedTag: String,
    viewModel: TimeLineViewModel,
    onTagClick: ((String) -> Unit)? = null,
    onRemoveTagClick: (() -> Unit)? = null,
    onRefreshing: ((Boolean) -> Unit)? = null,
) {

    LazyColumn(
        modifier = modifier,
    ){
        if (!isRefreshing) {
            item {
                if (selectedTag.isNotEmpty()) {
                    TagFilterView(
                        tagName = selectedTag,
                        onClick = onRemoveTagClick,
                        modifier = Modifier.padding(bottom = Spacing.medium)
                    )
                }
            }
            items(items) { items ->
                items?.let { timeLine ->
                    TimeLineItem(
                        viewModel = viewModel,
                        item = timeLine,
                        onTagClick = onTagClick
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
                        modifier = Modifier
                            .testTag("ShimmerGridLoading$index")
                    )
                }
            }
            is LoadState.Error -> Unit
        }
    }
}


@Composable
fun TimeLineItem(
    modifier: Modifier = Modifier,
    viewModel: TimeLineViewModel,
    item: UserPostResponse,
    onTagClick: ((String) -> Unit)?,
) {
    val context = LocalContext.current
    viewModel.checkFavorite(item.id.orEmpty()).collectAsState(initial = RoomResult.Success(false)).value.let { state ->
            val isLiked = if (state is RoomResult.Success) state.data else false
            TimeLineCard(
                item = item,
                modifier = modifier.padding(bottom = Spacing.normal),
                onClick = onTagClick,
                isLiked = isLiked,
                onLikeClick = { post ->
                    val message = if (isLiked) {
                        viewModel.deleteFavorite(post)
                        "Success delete from favorite"
                    } else {
                        viewModel.saveFavorite(post)
                        "Success save to favorite"
                    }
                    context.toast(message)
                }
            )
        }
}