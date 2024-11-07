package com.nextvocab.nextvocab.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nextvocab.nextvocab.R
import com.nextvocab.nextvocab.presentation.navigation.Screen
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor
import com.nextvocab.nextvocab.presentation.ui.theme.ItemColor
import com.nextvocab.nextvocab.presentation.ui.theme.gradientPurpleColor1
import com.nextvocab.nextvocab.presentation.ui.theme.gradientPurpleColor2

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackColor)
            .padding(start = 12.dp, end = 12.dp, top = 24.dp)
    ) {
        Button(
            modifier = Modifier
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = ItemColor),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                navController.navigate(Screen.SearchScreen.route)
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text =stringResource(id = R.string.search))
            }
        }

        GradientButton(
            text = stringResource(id = R.string.study),
            gradient = gradientPurpleColor1,
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp)
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = {}
        )
        GradientButton(
            text = stringResource(id = R.string.add_a_new_card),
            gradient = gradientPurpleColor2,
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp)
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                navController.navigate(Screen.AddScreen.route)
            }
        )
    }
}


