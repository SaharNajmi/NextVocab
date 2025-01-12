package com.nextvocab.nextvocab.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.presentation.navigation.NavigationItem
import com.nextvocab.nextvocab.presentation.navigation.Screen
import com.nextvocab.nextvocab.presentation.ui.WordHeader
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor

@Composable
fun CardDetailScreen(
    navController: NavController,
    item: WordEntity
) {
    val formattedMeaning = item.meaning?.joinToString("\n") { "* $it" }

    val formattedExample = item.example?.mapIndexed { index, item ->
        "${index + 1}. $item"
    }?.joinToString("\n")
    val formattedText = "$formattedMeaning\n\n$formattedExample"
    var textFieldValue by remember { mutableStateOf(formattedText) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackColor)
            .padding(start = 12.dp, end = 12.dp, top = 24.dp),
    ) {
        WordHeader(name = item.word, partOfSpeak = item.partOfSpeak,
            onCancelClick = {
                navController.navigate(Screen.HOME.name)
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

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                //todo edit call
                navController.navigate(NavigationItem.Home.route)
            }
        ) {
            Text("Apply")
        }
    }
}