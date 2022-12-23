package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.theme.HorizontalSpace
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    isLike: Boolean,
    onClick: (() -> Unit)? = null
) {
    var state by rememberSaveable { mutableStateOf(isLike) }

    Row(
        modifier = modifier.clickable {
           state = !state
            onClick?.invoke()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = if (state) R.drawable.ic_favorite else R.drawable.ic_favorite_border),
            contentDescription = null,
            tint = MaterialTheme.colors.primary
        )
        HorizontalSpace(space = Spacing.small)
        Text(
            text = "Liked",
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}