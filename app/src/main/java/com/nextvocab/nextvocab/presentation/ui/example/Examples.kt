package com.nextvocab.nextvocab.presentation.ui.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.presentation.navigation.NavigationItem
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.WordHeader
import com.nextvocab.nextvocab.presentation.ui.theme.BackColor
import com.nextvocab.nextvocab.presentation.ui.theme.Purple40
import kotlinx.coroutines.launch

@Composable
fun ExampleSelectionScreen(
    sharedViewModel: SharedViewModel,
    exampleViewModel: ExampleViewModel,
    onBackClick:()->Unit,
    onCancelClick:()->Unit
) {
    val wordDefinition=sharedViewModel.wordDefinition.collectAsStateWithLifecycle()
    var items by rememberSaveable {
        mutableStateOf(
            exampleViewModel.examples ?: wordDefinition.value?.examples
        )
    }


    var newExample by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackColor)
            .padding(18.dp)
    ) {
        Row {
            IconButton(
                onClick = {
                    exampleViewModel.examples = items
                   onBackClick()
                }
            ) {
                Icon(Icons.Outlined.ArrowBack, contentDescription = "", tint = Purple40)
            }
            WordHeader(name = wordDefinition.value!!.name,
                partOfSpeak = wordDefinition.value!!.partOfSpeak,
                onCancelClick = {
                    sharedViewModel.resetWordDefinition()
                    exampleViewModel.resetYourSteps()
                  onCancelClick()
                })
        }

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
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            TextField(
                value = newExample,
                onValueChange = {
                    newExample = it
                },
                label = {
                    Text(
                        "Add your example",
                        style = TextStyle(fontSize = 12.sp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .weight(1f), maxLines = 3
            )
            IconButton(modifier = Modifier
                .background(Purple40, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                .padding(start = 10.dp)
                .fillMaxHeight(), onClick = {
                items = buildList {
                    add(ExampleModel(example = newExample, isCheck = true))
                    items?.let { addAll(it) }
                }
                newExample = ""
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }
            ) {
                Icon(Icons.Outlined.Add, contentDescription = "add", tint = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            ShowExamples(
                modifier = Modifier.weight(1f),
                items = items?: listOf(),
                state = listState
            ) { isChecked, checkedIndex ->
                items = items?.mapIndexed { index, exampleModel ->
                    if (index == checkedIndex)
                        exampleModel.copy(isCheck = isChecked)
                    else
                        exampleModel
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    val examples = items?.filter { it.isCheck }?.map { it.example }
                        ?.map { ExampleModel(example = it, isCheck = false) }
                    sharedViewModel.wordDefinition.value?.run {
                        sharedViewModel.insertWord(
                            Word(
                                name = name,
                                meaning = sharedViewModel.meanings.map {
                                    MeaningModel(
                                        meaning = it,
                                        isCheck = true
                                    )
                                },
                                partOfSpeak = partOfSpeak,
                                examples = examples ?: listOf()
                            )
                        )
                    }
                    sharedViewModel.resetWordDefinition()
                    exampleViewModel.resetYourSteps()
                  onCancelClick()
                }
            ) {
                Text("ADD")
            }
        }
    }
}

@Composable
fun ShowExamples(
    modifier: Modifier = Modifier,
    items: List<ExampleModel>,
    state: LazyListState,
    onCheckedChange: (Boolean, Int) -> Unit
) {
    LazyColumn(modifier = modifier, state) {
        items(items.size, key = { items[it].id }) { index ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            ) {
                Checkbox(
                    checked = items[index].isCheck,
                    onCheckedChange = {
                        onCheckedChange(it, index)
                    }
                )
                Text(
                    text = items[index].example,
                    style = TextStyle(fontSize = 14.sp, color = Color.White)
                )
            }
        }
    }
}