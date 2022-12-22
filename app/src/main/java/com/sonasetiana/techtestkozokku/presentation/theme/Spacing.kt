package com.sonasetiana.techtestkozokku.presentation.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Spacing {
    val Space1 = 1.dp
    val Space2 = 2.dp
    val Space4 = 4.dp
    val Space8 = 8.dp
    val Space10 = 10.dp
    val Space12 = 12.dp
    val Space16 = 16.dp
    val Space18 = 18.dp
    val Space20 = 20.dp
    val Space24 = 24.dp
    val Space32 = 32.dp
    val Space48 = 48.dp
    val Space56 = 56.dp
    val Space64 = 64.dp
    val Space72 = 72.dp
    val Space96 = 96.dp

    val xSmall = Space2
    val small = Space4
    val normal = Space8
    val xNormal = Space12
    val medium = Space16
    val large = Space24
    val xLarge = Space32
    val big = Space48
}

@Composable
fun HorizontalSpace(
    space: Dp
) {
    Spacer(modifier = Modifier.width(space))
}

@Composable
fun VerticalSpace(
    space: Dp
) {
    Spacer(modifier = Modifier.height(space))
}
