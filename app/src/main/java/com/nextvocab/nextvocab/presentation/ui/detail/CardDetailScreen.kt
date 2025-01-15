package com.nextvocab.nextvocab.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel
import com.nextvocab.nextvocab.presentation.navigation.NavigationItem
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.WordHeader
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor

@Composable
fun CardDetailScreen(
    navController: NavController,
    wordName: String,
    sharedViewModel: SharedViewModel,
    detailViewModel: WordDetailViewModel
) {
    val word = detailViewModel.getWordById(wordName).collectAsStateWithLifecycle(null)
    word.value?.let { it ->
        val formattedMeaning = it.meaning.map { it.meaning }.joinToString("\n") { "* $it" }

        val formattedExample = it.examples.map { it.example }.mapIndexed { index, item ->
            "${index + 1}. $item"
        }.joinToString("\n")
        val formattedText = "$formattedMeaning\n\n$formattedExample"
        var textFieldValue by remember { mutableStateOf(formattedText) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(BackColor)
                .padding(start = 12.dp, end = 12.dp, top = 24.dp),
        ) {
            WordHeader(name = it.name, partOfSpeak = it.partOfSpeak,
                onCancelClick = {
                    navController.navigate(NavigationItem.Home)
                })

            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                textStyle = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Start),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                ),
                shape = RoundedCornerShape(6.dp),
                value = textFieldValue,
                onValueChange = { textFieldValue = it })

            Row {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 12.dp, end = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        Regex
                        val meanings = textFieldValue.lines()
                            .filter { it.startsWith("*") }
                            .map { it.removePrefix("*").trim() }
                        val examples = textFieldValue.lines()
                            .filter { it.matches(Regex("""\d+\..*""")) }
                            .map { it.replaceFirst(Regex("""^\d+\."""), "").trim() }
                        val newItem = it.copy(meaning = meanings.map {
                            MeaningModel(
                                meaning = it,
                                isCheck = true
                            )
                        }, examples = examples.map { ExampleModel(example = it, isCheck = true) })
                        sharedViewModel.updateWord(newItem)
                        navController.navigate(NavigationItem.Home)
                    }
                ) {
                    Text("Edit")
                }

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 12.dp, start = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        sharedViewModel.deleteWord(it)
                        navController.navigate(NavigationItem.Home)
                    }
                ) {
                    Text("Delete")
                }
            }
        }
    }
}