package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing

@Composable
fun LoadingMore(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth().wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(Spacing.Space56).padding(Spacing.medium),
            strokeWidth = Spacing.Space2
        )
    }
}