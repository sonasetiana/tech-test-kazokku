package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing
import com.sonasetiana.techtestkozokku.presentation.theme.TechTestKozokkuTheme

@Composable
fun TagView(
    modifier: Modifier = Modifier,
    tagName: String
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(Spacing.large),
        onClick = {

        },
    ) {
        Text(text = tagName)
    }
}
@Composable
fun TagFilterView(
    modifier: Modifier = Modifier,
    tagName: String
) {
    Box(modifier = modifier) {
        TagView(tagName = tagName, modifier = Modifier.padding(
            top = Spacing.medium,
            end = Spacing.medium
        ))
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {  }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewTagView() {
    TechTestKozokkuTheme {
        TagFilterView(tagName = "Animal")
    }
}