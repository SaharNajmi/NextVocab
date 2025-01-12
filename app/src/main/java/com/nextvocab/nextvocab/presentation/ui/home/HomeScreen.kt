package com.nextvocab.nextvocab.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.nextvocab.nextvocab.R
import com.nextvocab.nextvocab.presentation.navigation.NavigationItem
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.GradientButton
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor2
import com.nextvocab.nextvocab.presentation.ui.theme.gradientPurpleColor1
import com.nextvocab.nextvocab.presentation.ui.theme.gradientPurpleColor2
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var searchInput by remember { mutableStateOf("") }
    val items = sharedViewModel.words

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(BackColor)
            .padding(start = 12.dp, end = 12.dp, top = 24.dp),
    ) {
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
                navController.navigate(NavigationItem.FrontSide.route)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        SearchBar(
            query = searchInput,
            onQueryChange = { newQuery -> searchInput = newQuery },
            onCloseSearch = {
                searchInput = ""
                navController.popBackStack()
            }
        )

        LazyColumn(modifier = Modifier.padding(top = 12.dp, start = 4.dp, end = 4.dp)) {
            val filteredItems = if (searchInput.isEmpty()) {
                items.value
            } else {
                items.value.filter { it.word.contains(searchInput) }
            }
            items(filteredItems.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BackColor2),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                )
                {
                    Text(
                        text = filteredItems[index].word,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .padding(12.dp),
                        style = TextStyle(color = Color.White)
                    )
                    IconButton(onClick = {
                        val jsonString = URLEncoder.encode(Gson().toJson(filteredItems[index]), "UTF-8").replace("+", "%20")
                        navController.navigate("${NavigationItem.Detail.route}/${jsonString}")
                    }) {
                        Icon(Icons.Outlined.Edit, contentDescription = "edit", tint = Color.White)
                    }
                }
            }
        }
    }
}


