package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing

@Composable
fun UserCard(
    fullName: String,
    picture: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().height(200.dp),
        elevation = Spacing.normal,
    ) {
        Column{
            AsyncImage(
                model = picture,
                contentDescription = picture.split("/").last(),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Text(
                text = fullName,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(Spacing.normal)
            )
        }
    }

}
