package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.nextvocab.nextvocab.data.response.ApiResponse
import com.nextvocab.nextvocab.presentation.navigation.Screen
import com.nextvocab.nextvocab.presentation.ui.theme.Purple40
import com.nextvocab.nextvocab.presentation.ui.theme.PurpleGrey90
import com.nextvocab.nextvocab.presentation.ui.theme.darkGreen
import com.nextvocab.nextvocab.presentation.viewmodel.WordViewModel

@Composable
fun WordDefinitionScreen(navController: NavController, viewModel: WordViewModel) {

    Column(modifier = Modifier.padding(16.dp)) {
        val text = remember { mutableStateOf("") }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ), onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Cancel", style = TextStyle(fontSize = 14.sp, color = Color.Red))
            }
            Button(contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ), onClick = {}
            ){
                Text("Save", style = TextStyle(fontSize = 14.sp, color = darkGreen))
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextField(value = text.value, onValueChange = { text.value = it },
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
                label = { Text("FRONT SIDE", style = TextStyle(fontSize = 12.sp)) })
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search_icon),
                tint = Color.Black,
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                        viewModel.fetchWordDefinition(text.value)
                        text.value = ""
                    },
            )

        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            "BACK SIDE", style = TextStyle(fontSize = 12.sp, color = Purple40),
            modifier = Modifier.padding(start = 12.dp)
        )
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
            when (val result = viewModel.wordDefinition) {
                is ApiResponse.Loading -> {
                    CircularProgressIndicator()
                }

                is ApiResponse.Success -> {
                    val wordDefinition = result.data ?: run {
                        Text(text = "No definition available", color = Color.Gray)
                        return
                    }
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

                is ApiResponse.Error -> {
                    Text(text = "Error: ${result.error}", color = Color.Red)
                }

                null -> {
                    //
                }
            }
        }

    }
}

@Composable
fun ItemRowList(examples: List<String>?) {
    Column(modifier = Modifier.padding(16.dp)) {
        examples?.forEach { example ->
            Text(text = example)
            Spacer(modifier = Modifier.height(4.dp)) // Optional: Adds space between items
        }
    }
}