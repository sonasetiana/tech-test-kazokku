package com.sonasetiana.techtestkozokku.presentation.modules.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.data.model.UserDetailResponse
import com.sonasetiana.techtestkozokku.presentation.common.UiState
import com.sonasetiana.techtestkozokku.presentation.components.ShimmerGridLoading
import com.sonasetiana.techtestkozokku.presentation.components.TimeLineCard
import com.sonasetiana.techtestkozokku.presentation.components.TopSearchBar
import com.sonasetiana.techtestkozokku.presentation.theme.HorizontalSpace
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.VerticalSpace
import org.koin.androidx.compose.koinViewModel

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
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
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
                    timeLines?.let { data ->
                        LazyColumn(
                            contentPadding = PaddingValues(Spacing.medium)
                        ) {
                            item {
                                DetailUserInfo(user = uiState.data)
                            }
                            if (keyword.isNotEmpty()) {
                                val filter = timeLines.itemSnapshotList.filter { key ->
                                    key?.text?.lowercase()?.contains(keyword) == true
                                }.toList()
                                items(filter.size) { index ->
                                    filter[index]?.let { tm -> TimeLineCard(item = tm, modifier = Modifier.padding(bottom = Spacing.medium)) }
                                }
                            } else {
                                items(timeLines) { tm ->
                                    tm?.let { TimeLineCard(item = it, modifier = Modifier.padding(bottom = Spacing.medium)) }
                                }
                            }

                            /**
                             * State when Paging initial load
                             */
                            when(timeLines.loadState.refresh) {
                                is LoadState.NotLoading -> {
                                    //refreshing = false
                                }
                                is LoadState.Loading -> {
                                    //refreshing = true
                                    items(5) {
                                        ShimmerGridLoading()
                                    }
                                }
                                is LoadState.Error -> item {
                                    Text(text = "Error")
                                }
                            }
                        }
                    }
                }
            }
            is UiState.Error -> {
                Text(text = uiState.message)
            }
        }
    }

}

@Composable
fun DetailUserInfo(
    modifier: Modifier = Modifier,
    user: UserDetailResponse
) {
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
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person_added),
                    contentDescription = null,
                    tint = Color.Green,
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