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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextvocab.nextvocab.presentation.viewmodel.WordViewModel

@Composable
fun WordDefinitionScreen(viewModel: WordViewModel) {

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

        viewModel.wordDefinition?.let { definition ->
            Text(
                text = definition.word,
                color = Color.Magenta,
                fontSize = 18.sp,
            )
            Text(text = "MEANINGS")
            ItemRowList(examples = definition.meaning)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "EXAMPLES")

            ItemRowList(examples = definition.example)
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