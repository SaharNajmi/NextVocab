package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor

@Composable
fun WordHeader(wordModel: DomainWordDefinition,
               onCancelClick: () -> Unit,) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = wordModel.word,
            modifier = Modifier.padding(end = 4.dp),
            style = TextStyle(fontSize = 16.sp, color = Color.White,fontWeight = FontWeight.Bold),
        )
        Text(
            text = wordModel.partOfSpeak,
            modifier = Modifier.weight(1f),
            style = TextStyle(fontSize = 12.sp, color = Color.White, ),
        )
        Button(colors = ButtonDefaults.buttonColors(
            containerColor = BackColor
        ), onClick = {
            onCancelClick()
        }) { Text("Cancel", style = TextStyle(color = Color.White)) }
    }
}