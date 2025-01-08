package com.nextvocab.nextvocab.presentation.ui.front

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nextvocab.nextvocab.data.response.Loadable
import com.nextvocab.nextvocab.presentation.navigation.NavigationItem
import com.nextvocab.nextvocab.presentation.ui.LoadingButton
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor
import com.nextvocab.nextvocab.presentation.ui.theme.Purple80
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel

@Composable
fun FrontSideScreen(navController: NavController, viewModel: SharedViewModel) {
    val text = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackColor)
            .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FRONT SIDE",
            style = TextStyle(fontSize = 24.sp, color = Color.White),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        BasicTextField(
            value = text.value, onValueChange = {
                text.value = it
            },
            textStyle = TextStyle(fontSize = 20.sp, color = Color.White),
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 1,
            decorationBox = { innerText ->
                if (text.value.isEmpty()) {
                    Text(
                        text = "Enter the word",
                        style = TextStyle(fontSize = 20.sp, color = Color.Gray),
                        modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                    )
                }
                innerText()
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Purple80)
        )

        Spacer(modifier = Modifier.height(38.dp))
        LoadingButton(
            text = "Next",
            modifier = Modifier.fillMaxWidth(),
            isLoading = viewModel.uiState is Loadable.Loading,
            onClick = {
                if (text.value.isNotEmpty()) {
                    viewModel.fetchWordDefinition(text.value)
                }
            })

        when (val result = viewModel.uiState) {
            is Loadable.Error -> {
                Log.e("ERROR", "We can't find this, try another one")
            }

            is Loadable.Success -> {
                navController.navigate(NavigationItem.MeaningNavigationItem.route)
            }

            else -> {}
        }

    }
}
