package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nextvocab.nextvocab.R
import com.nextvocab.nextvocab.data.response.Loadable
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor
import com.nextvocab.nextvocab.presentation.ui.theme.PurpleGrey90
import com.nextvocab.nextvocab.presentation.viewmodel.WordViewModel

@Composable
fun SearchScreen(navController: NavController, viewModel: WordViewModel) {

    Column(modifier = Modifier.background(BackColor).padding(16.dp)) {
        val text = remember { mutableStateOf("") }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clickable {
                        navController.popBackStack()
                    },
            )
            TextField(value = text.value, onValueChange = { text.value = it },
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
                label = { Text("Search the word", style = TextStyle(fontSize = 12.sp)) })
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search_icon),
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        viewModel.fetchWordDefinition(text.value)
                        text.value = ""
                    },
            )

        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))
//        Text(
//            "BACK SIDE", style = TextStyle(fontSize = 12.sp, color = Purple40),
//            modifier = Modifier.padding(start = 12.dp)
//        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(PurpleGrey90)
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
        ) {

            when (val result = viewModel.uiState ) {
                is Loadable.Loading -> {
                    CircularProgressIndicator()
                }

                is Loadable.Success -> {
                    val wordDefinition = result.data

                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = wordDefinition.word,
                            color = Color.Magenta,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(text = "MEANINGS")
                        ItemRowList(examples = wordDefinition.meaning)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "EXAMPLES")
                        ItemRowList(examples = wordDefinition.example)
                    }
                }

                is Loadable.Error -> {
                    Text(text = "Error: ${result.error}", color = Color.Red)
                }

                else -> {
                    //
                }
            }
        }

    }
}

@Composable
fun <T>ItemRowList(examples: List<T>?) {
    Column(modifier = Modifier.padding(16.dp)) {
        examples?.forEach { example ->
            Text(text = example.toString())
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}