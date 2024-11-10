package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
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
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor

@Composable
fun ExampleSelectionScreen(navController: NavController, wordModel: DomainWordDefinition?) {
    val items = wordModel?.example!!
    val checkedStates = remember { items.map { mutableStateOf(false) } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackColor)
            .padding(18.dp)
    ) {
        Text(
            text = wordModel.word,
            style = TextStyle(fontSize = 14.sp, color = Color.White),
            modifier = Modifier.align(Alignment.Start)
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "EXAMPLES",
            style = TextStyle(fontSize = 24.sp, color = Color.White),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Choose the examples you want",
            style = TextStyle(fontSize = 18.sp, color = Color.White),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.padding(bottom = 56.dp)) {
                items(items.size) { index ->
                    Row(
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = checkedStates[index].value,
                            onCheckedChange = { checkedStates[index].value = it })
                        Text(
                            text = items[index],
                            style = TextStyle(color = Color.White, fontSize = 14.sp)
                        )
                    }
                }
            }
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    //todo
                }
            ) {
                Text("Next")
            }
        }
    }
}