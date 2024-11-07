package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor

@Composable
fun AddCardScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackColor)
    ) {
        FrontSideScreen()
    }
}