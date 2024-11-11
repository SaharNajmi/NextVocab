package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton(text: String, modifier: Modifier, isLoading: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { if (!isLoading) onClick() },
        enabled = !isLoading,
        modifier = modifier
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(12.dp)
            )
        } else {
            Text(text = text)
        }
    }
}