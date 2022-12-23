package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sonasetiana.techtestkozokku.data.model.UserPostResponse
import com.sonasetiana.techtestkozokku.presentation.theme.HorizontalSpace
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme
import com.sonasetiana.techtestkozokku.presentation.theme.VerticalSpace

@Composable
fun TimeLineCard(
    modifier: Modifier = Modifier,
    item: UserPostResponse,
    onClick: ((String) -> Unit)? = null,
    isLiked: Boolean,
    onLikeClick: ((UserPostResponse) -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = Spacing.xNormal
    ) {
        Column {
            TimeLineHeader(
                picture = item.owner?.picture.orEmpty(),
                fullName = "${item.owner?.title} ${item.owner?.firstName} ${item.owner?.lastName}",
                publishDate = item.publishDate.orEmpty(),
                modifier = Modifier.padding(Spacing.medium)
            )
            Card(
                elevation = 0.dp,
                modifier = Modifier.padding(horizontal = Spacing.medium)
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )
            }
            VerticalSpace(space = Spacing.normal)
            Column(
                modifier = Modifier.padding(Spacing.medium)
            ) {

                TimeLineTagList(
                    tagList = item.tags,
                    onClick = { tagName ->
                        onClick?.invoke(tagName)
                    }
                )
                VerticalSpace(space = Spacing.medium)
                Text(
                    text = item.text.orEmpty(),
                    style = MaterialTheme.typography.body1,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                VerticalSpace(space = Spacing.small)
                Text(
                    text = "${item.likes} Likes",
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Red,
                    )
                )
                VerticalSpace(space = Spacing.medium)
                Divider()
                VerticalSpace(space = Spacing.medium)
                LikeButton(
                    modifier = Modifier.align(Alignment.End),
                    isLike = isLiked,
                    onClick = {
                        onLikeClick?.invoke(item)
                    }
                )
            }
        }
    }
}

@Composable
fun TimeLineHeader(
    modifier: Modifier = Modifier,
    picture: String,
    fullName: String,
    publishDate: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = picture,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(Spacing.big)
                .clip(CircleShape)
        )
        HorizontalSpace(space = Spacing.medium)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = fullName,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            VerticalSpace(space = Spacing.small)
            Text(
                text = publishDate,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.primary
                )
            )
        }

    }
}

@Composable
fun TimeLineTagList(
    modifier: Modifier = Modifier,
    tagList: List<String>,
    onClick: ((String) -> Unit)? = null
) {
    Box(modifier = modifier.fillMaxWidth()){
        FlewRow(
            horizontalGap = Spacing.xSmall,
        ) {
            tagList.map { TagView(
                    tagName = it,
                    modifier = Modifier.padding(end = Spacing.small),
                    onClick = {
                        onClick?.invoke(it)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTagView() {
    TechTestKozokkuTheme {

    }
}