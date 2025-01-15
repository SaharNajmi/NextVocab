package com.nextvocab.nextvocab.presentation.ui.study

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nextvocab.nextvocab.presentation.navigation.NavigationItem
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.WordHeader
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor

@Composable
fun Study(
    sharedViewModel: SharedViewModel,
    onBackClick:()->Unit
) {
    val words by sharedViewModel.getWords().collectAsStateWithLifecycle(emptyList())

    var count by remember { mutableStateOf(0) }
    var frontCard by remember { mutableStateOf(words[count]) }

    val formattedMeaning = frontCard.meaning.joinToString("\n") { "* $it" }

    val formattedExample = frontCard.examples.mapIndexed { index, item ->
        "${index + 1}. $item"
    }?.joinToString("\n")

    val backCard = "$formattedMeaning\n\n$formattedExample"
    var showAnswer by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackColor)
            .padding(start = 12.dp, end = 12.dp, top = 24.dp),
    ) {
        WordHeader(name = frontCard.name, partOfSpeak = frontCard.partOfSpeak,
            onCancelClick = {
                onBackClick()
            })

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        if (showAnswer)
            Text(
                text = backCard, style = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp, end = 8.dp)
            ) else {
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
        }

        if (showAnswer) {
            Row {
                Button(modifier = Modifier.padding(4.dp),
                    onClick = {

                    }) {
                    Text("Again")
                }
                Button(modifier = Modifier.padding(4.dp),
                    onClick = {

                    }) {
                    Text("Hard")
                }
                Button(modifier = Modifier.padding(4.dp),
                    onClick = {

                    }) {
                    Text("Good")
                }
                Button(modifier = Modifier.padding(4.dp),
                    onClick = {
                        if (words.size > count)
                            frontCard = words[count++]

                    }) {
                    Text("Easy")
                }
            }
        } else {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), shape = RoundedCornerShape(12.dp),
                onClick = {
                    showAnswer = true
                    frontCard = words[count++]
                }
            ) {
                Text("Show Answer")
            }
        }
    }
}