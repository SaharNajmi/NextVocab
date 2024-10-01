package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextvocab.nextvocab.presentation.viewmodel.WordViewModel

@Composable
fun WordDefinitionScreen(viewModel: WordViewModel) {
    val wordDefinition = viewModel.wordDefinitionState

    Column(modifier = Modifier.padding(16.dp)) {
        var text = remember { mutableStateOf("") }

        Row {
            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                label = { Text("Enter word...") }
            )
            Button(onClick = {
                viewModel.fetchWordDefinition(text.value)
                text.value = ""
            }) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        wordDefinition.forEach { definition ->
            Text(
                text = "${definition.word}: ${
                    definition.phonetic ?: definition.phonetics.mapNotNull { it.text }
                        .joinToString()
                }",
                color = Color.Magenta,
                fontSize = 18.sp,
            )
            definition.meanings.forEach {
                it.definitions.forEach {  mean ->
                    Text(text = "* ${mean.definition}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}