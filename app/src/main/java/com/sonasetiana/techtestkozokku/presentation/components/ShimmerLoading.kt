package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing

@Composable
fun ShimmerLoading(
    content: @Composable (Brush) -> Unit
) {
    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnim by transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    content(brush)
}

@Composable
fun ShimmerGridLoading(
    modifier: Modifier = Modifier
) {
    ShimmerLoading { brush ->
        Spacer(modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(Spacing.small)
            .clip(RoundedCornerShape(Spacing.small))
            .background(brush)
        )
    }
}