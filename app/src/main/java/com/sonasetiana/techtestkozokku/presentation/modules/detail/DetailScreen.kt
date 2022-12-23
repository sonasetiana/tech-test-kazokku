package com.sonasetiana.techtestkozokku.presentation.modules.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    userId: String
) {
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
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                    viewModel.getDetail(userId)
                }
                is UiState.Success -> {
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
                                onAddUserClick = { id ->
                                    if (isUserAdded) {
                                        viewModel.deleteUser(id)
                                    } else {
                                        viewModel.saveUser(id)
                                    }
                                }
                            )
                        }
                    }
                }
                is UiState.Error -> {
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
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Spacing.medium)
    ) {
        item {
            DetailUserInfo(
                user = userData,
                isAdded = isUserAdded,
                onClick = onAddUserClick
            )
        }
        if (searchKeyword.isNotEmpty()) {
            val filter = items.itemSnapshotList.filter { key ->
                key?.text?.lowercase()?.contains(searchKeyword) == true
            }.toList()
            items(filter.size) { index ->
                filter[index]?.let { tm -> TimeLineCard(item = tm, modifier = Modifier.padding(bottom = Spacing.medium), isLiked = false) }
            }
        } else {
            items(items) { tm ->
                tm?.let { TimeLineCard(item = it, modifier = Modifier.padding(bottom = Spacing.medium), isLiked = false) }
            }
        }

        /**
         * State when Paging initial load
         */
        when(items.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                items(5) {
                    ShimmerGridLoading()
                }
            }
            is LoadState.Error -> Unit
        }
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
                    text = "${user.title} ${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            HorizontalSpace(space = Spacing.normal)
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
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
        TextInfo(label = "Gender", value = user.gender.orEmpty())
        TextInfo(label = "Date of birth", value = user.dateOfBirth.orEmpty())
        TextInfo(label = "Join from", value = user.registerDate.orEmpty())
        TextInfo(label = "Email", value = user.email.orEmpty())
        TextInfo(label = "Address", value = "${user.location?.country}, ${user.location?.street}, ${user.location?.city}, ${user.location?.state}")
    }
}

@Composable
fun TextInfo(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
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
            maxLines = 1,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis
        )
    }
}