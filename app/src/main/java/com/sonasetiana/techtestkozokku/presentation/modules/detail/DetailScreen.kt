package com.sonasetiana.techtestkozokku.presentation.modules.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.data.local.db.RoomResult
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.presentation.common.UiState
import com.sonasetiana.techtestkozokku.presentation.components.ErrorView
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.TimeLineCard
import com.sonasetiana.techtestkozokku.presentation.components.TopSearchBar
import com.sonasetiana.techtestkozokku.presentation.theme.HorizontalSpace
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.VerticalSpace
import com.sonasetiana.techtestkozokku.utils.toast
import com.sonasetiana.techtestkozokku.utils.upFirst
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    userId: String
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<DetailViewModel>()

    val timeLines = viewModel.timeLine?.collectAsLazyPagingItems()

    var keyword by remember {
        mutableStateOf("")
    }

    var isUserAdded by remember {
        mutableStateOf(false)
    }

    val swipeRefreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    fun onRefresh() = swipeRefreshScope.launch {
        viewModel.getDetail(userId)
    }
    val swipeRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = ::onRefresh
    )

    /**
     * checking user if added
     */
    viewModel.checkUser(userId).collectAsState(initial = RoomResult.Success(false)).value.let { state ->
        isUserAdded = when(state){
            is RoomResult.Success -> {
                state.data
            }
            is RoomResult.Failure -> {
                false
            }
        }

    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    isRefreshing = true
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center).testTag("DetailLoading")
                    )
                    viewModel.getDetail(userId)
                }
                is UiState.Success -> {
                    isRefreshing = false
                    timeLines?.refresh()
                    Column(modifier = modifier) {
                        TopSearchBar(
                            keyword = keyword,
                            onValueChange = { text ->
                                keyword = text
                            },
                            onClear = {
                                keyword = ""
                            },
                            onBackPress = {
                                navController.navigateUp()
                            }
                        )
                        timeLines?.let { timeLineData ->
                            TimeLineListView(
                                modifier = Modifier.pullRefresh(swipeRefreshState),
                                items = timeLineData,
                                userData = uiState.data,
                                isUserAdded = isUserAdded,
                                searchKeyword = keyword,
                                viewModel = viewModel,
                                onAddUserClick = { id ->
                                    val message = if (isUserAdded) {
                                        viewModel.deleteUser(id)
                                        "Success delete ${uiState.data.fullName}"
                                    } else {
                                        viewModel.saveUser(id)
                                        "Success add ${uiState.data.fullName}"
                                    }
                                    context.toast(message)
                                }
                            )
                        }
                    }
                }
                is UiState.Error -> {
                    isRefreshing = false
                    ErrorView(
                        message = uiState.message,
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {
                            viewModel.getDetail(userId)
                        }
                    )
                }
            }
        }


        PullRefreshIndicator(isRefreshing, swipeRefreshState, Modifier.align(Alignment.TopCenter))
    }

}

@Composable
fun TimeLineListView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<UserPostResponse>,
    userData: UserDetailResponse,
    isUserAdded: Boolean,
    searchKeyword: String,
    onAddUserClick: ((String) -> Unit)? = null,
    viewModel: DetailViewModel
) {


    LazyColumn(
        modifier = modifier.testTag("PostUserList"),
    ) {
        item {
            DetailUserInfo(
                user = userData,
                isAdded = isUserAdded,
                onClick = onAddUserClick,
                modifier = Modifier.padding(Spacing.medium)
            )
        }
        if (searchKeyword.isNotEmpty()) {
            val filter = items.itemSnapshotList.filter { key ->
                key?.text?.lowercase()?.contains(searchKeyword) == true
            }.toList()
            items(filter.size) { index ->
                filter[index]?.let {  item ->
                    TimelineItem(item = item, viewModel = viewModel)
                }
            }
        } else {
            items(items) { item ->
                item?.let {
                    TimelineItem(item = item, viewModel = viewModel)
                }
            }
        }

        /**
         * State when Paging initial load
         */
        when(items.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                items(5) { index ->
                    ShimmerGridLoading(
                        modifier = Modifier.testTag("ShimmerGridLoading$index")
                    )
                }
            }
            is LoadState.Error -> Unit
        }
    }
}

@Composable
fun TimelineItem(
    item: UserPostResponse,
    viewModel: DetailViewModel
) {
    val context = LocalContext.current
    viewModel.checkFavorite(item.id.orEmpty()).collectAsState(initial = RoomResult.Success(false)).value.let { state ->
        val isLiked = if (state is RoomResult.Success) state.data else false
        TimeLineCard(
            item = item,
            modifier = Modifier.padding(bottom = Spacing.medium),
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

@Composable
fun DetailUserInfo(
    modifier: Modifier = Modifier,
    user: UserDetailResponse,
    isAdded: Boolean,
    onClick: ((String) -> Unit) ? = null
) {
    var isAccountAdded by rememberSaveable {
        mutableStateOf(isAdded)
    }
    Column(modifier = modifier.padding(Spacing.medium)) {
        Box(
            modifier = Modifier
                .width(220.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = user.picture,
                    contentDescription = "User Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(Spacing.Space96)
                        .clip(CircleShape)
                )
                VerticalSpace(space = Spacing.Space8)
                Text(
                    text = user.fullName,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            HorizontalSpace(space = Spacing.normal)
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart).testTag("AddUserButton"),
                onClick = {
                    isAccountAdded = !isAccountAdded
                    onClick?.invoke(user.id.orEmpty())
                }
            ) {
                Icon(
                    painter = painterResource(id = if (isAccountAdded) R.drawable.ic_person_added else R.drawable.ic_add_person),
                    contentDescription = null,
                    tint = if (isAccountAdded) Color.Green else Color.Blue,
                    modifier = Modifier.size(Spacing.big)
                )
            }
        }
        VerticalSpace(space = Spacing.medium)
        TextInfo(label = "Gender", value = user.gender.upFirst())
        TextInfo(label = "Date of birth", value = user.dateOfBirthFormatted)
        TextInfo(label = "Join from", value = user.joinDate)
        TextInfo(label = "Email", value = user.email.orEmpty())
        TextInfo(label = "Address", value = user.address)
    }
}

@Composable
fun TextInfo(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Text(
            text = "$label :",
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold
            )
        )
        HorizontalSpace(space = Spacing.small)
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            maxLines = 2,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis
        )
    }
}