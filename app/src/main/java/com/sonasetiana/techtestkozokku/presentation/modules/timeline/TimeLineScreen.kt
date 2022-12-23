package com.sonasetiana.techtestkozokku.presentation.modules.timeline

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.TagFilterView
import com.sonasetiana.techtestkozokku.presentation.components.TimeLineCard
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
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


    LazyColumn(
        modifier = modifier.pullRefresh(refreshState),
        contentPadding = PaddingValues(Spacing.medium)
    ){
        item {
            if (selectedTag.isNotEmpty()) {
                TagFilterView(
                    tagName = selectedTag,
                    onClick = {
                        selectedTag = ""
                        viewModel.setTagName(selectedTag)
                        timeLineItems.refresh()
                    },
                    modifier = Modifier.padding(bottom = Spacing.medium)
                )
            }
        }
        items(
            timeLineItems
        ) { items ->
            items?.let { timeLine ->
                viewModel.checkFavorite(timeLine.id.orEmpty()).collectAsState(initial = RoomResult.Success(false)).value.let { state ->
                    val isLiked = if (state is RoomResult.Success) state.data else false
                    TimeLineCard(
                        item = timeLine,
                        modifier = Modifier.padding(bottom = Spacing.medium),
                        onClick = { tagName ->
                            selectedTag = tagName
                            viewModel.setTagName(selectedTag)
                            timeLineItems.refresh()
                        },
                        isLiked = isLiked,
                        onLikeClick = { post ->
                            if (isLiked) {
                                viewModel.deleteFavorite(post)
                            } else {
                                viewModel.saveFavorite(post)
                            }
                        }
                    )
                }

            }
        }

        /**
         * State when Paging initial load
         */
        when(timeLineItems.loadState.refresh) {
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