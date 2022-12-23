package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.sonasetiana.techtestkozokku.R

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_state))
    val lottie = animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
    LottieAnimation(
        composition = composition,
        progress = {
            lottie.progress
        }
    )

}