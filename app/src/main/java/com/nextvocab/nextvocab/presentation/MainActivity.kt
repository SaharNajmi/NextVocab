package com.nextvocab.nextvocab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nextvocab.nextvocab.core.Constants
import com.nextvocab.nextvocab.domain.model.Vocab
import com.nextvocab.nextvocab.presentation.theme.NextVocabTheme
import com.nextvocab.nextvocab.presentation.util.Screen
import com.nextvocab.nextvocab.presentation.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NextVocabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterScreen()
//                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}


@Composable
fun CounterScreen(viewModel: MainViewModel = viewModel()) {
    val count = viewModel.count.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count")

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { viewModel.incrementCount() }) {
                Text(text = "Increment")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { viewModel.decrementCount() }) {
                Text(text = "Decrement")
            }
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.AddScreen.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(Screen.AddScreen.route) {
            AddScreen() { model ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    Constants.ResultKey,
                    model
                )
                navController.navigateUp()
            }

        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var itemList by rememberSaveable { mutableStateOf(listOf<Vocab>()) }
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val vocab = savedStateHandle?.get<Vocab>(Constants.ResultKey)

    vocab?.let {
        itemList = itemList + it
        savedStateHandle.remove<Vocab>(Constants.ResultKey)
    }
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.AddScreen.route)

            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color(0xFFFFFFFF)
            ), modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Add")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)
                .weight(1f)
        ) {
            items(itemList) { item ->
                Text(
                    text = item.name + "   :" + item.meaning,
                )
            }
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF5722),
                contentColor = Color(0xFFFFFFFF)
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Study")
        }
    }

}

@Composable
fun AddScreen(onNavigateBack: (Vocab) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onNavigateBack(Vocab(name = text))
                }
            ) {
                Icon(Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.Red)
            }

            IconButton(
                onClick = {
                    onNavigateBack(Vocab())
                }
            ) {
                Icon(Icons.Outlined.Add, contentDescription = "", tint = Color.Red)
            }
        }

        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            onValueChange = { newText ->
                text = newText
            }
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF673AB7),
                contentColor = Color(0xFFFFFFFF)
            ),
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Text(text = "Find Image")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF673AB7),
                contentColor = Color(0xFFFFFFFF)
            ),
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Text(text = "Select examples")
        }
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF673AB7),
                contentColor = Color(0xFFFFFFFF)
            ),
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Text(text = "Word Familiar")
        }


    }
}
