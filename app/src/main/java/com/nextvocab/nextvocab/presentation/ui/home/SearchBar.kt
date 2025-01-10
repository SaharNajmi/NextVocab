package com.nextvocab.nextvocab.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextvocab.nextvocab.R
import com.nextvocab.nextvocab.presentation.ui.theme.ItemColor
import com.nextvocab.nextvocab.presentation.ui.theme.Purple80

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onCloseSearch: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text(text = stringResource(id = R.string.search), style = TextStyle(color = Color.Gray, fontSize = 16.sp)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    tint = Color.White
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = onCloseSearch) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.cancel),
                            tint = Color.White
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = ItemColor,
                unfocusedContainerColor = ItemColor,
            ),
            shape = RoundedCornerShape(12.dp)
        )
    }
}