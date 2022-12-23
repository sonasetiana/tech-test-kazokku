package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.VerticalSpace

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    title: String = "Oops, Something Wrong",
    message: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        VerticalSpace(space = Spacing.normal)
        Text(
            text = message ?: stringResource(id = R.string.rto_message),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        VerticalSpace(space = Spacing.medium)
        TextButton(onClick = onClick) {
            Text(
                text = "Try Again",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.material.MaterialTheme.colors.primary
                )
            )
        }
    }
}