package com.sonasetiana.techtestkozokku.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sonasetiana.techtestkozokku.R
import com.sonasetiana.techtestkozokku.presentation.theme.Spacing


@Composable
fun SearchField(
    keyword: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = keyword,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(id = R.string.search_placeholder))
        },
        leadingIcon = {
            if (keyword.isEmpty()) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
        },
        trailingIcon = {
            if (keyword.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .padding(Spacing.normal)
            .fillMaxWidth()
            .heightIn(40.dp)
            .border(
                Spacing.Space1,
                color = Color.Gray,
                shape = RoundedCornerShape(Spacing.large)
            )
            .clip(RoundedCornerShape(Spacing.xNormal))
    )
}

@Composable
fun TopSearchBar(
    keyword: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onBackPress) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        SearchField(
            keyword = keyword,
            onValueChange =
            onValueChange, onClear = onClear,
            modifier = Modifier.weight(1f)
        )

    }
}