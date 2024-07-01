package com.nextvocab.nextvocab

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nextvocab.nextvocab.ui.theme.NextVocabTheme
import kotlinx.parcelize.Parcelize

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
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.MainScreen.route,
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
            AddScreen() {model->
                navController.previousBackStackEntry?.savedStateHandle?.set("resultKey",model)
                navController.navigateUp()
            }

        }
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object AddScreen : Screen("add_new_vocab")
}
@Parcelize
data class WordModel(
    val id: Int = 0,
    val name: String = "",
    val meaning: String = ""
) : Parcelable

@Composable
fun MainScreen(navController: NavController) {
    var itemList by rememberSaveable { mutableStateOf(listOf<WordModel>()) }
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val wordModel = savedStateHandle?.get<WordModel>("resultKey")

    wordModel?.let {
        itemList = itemList + it
        savedStateHandle.remove<WordModel>("resultKey")
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
fun AddScreen(onNavigateBack: (WordModel) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                }
            )
            Button(
                onClick = {
                    onNavigateBack(WordModel(name = text, meaning = "test"))
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color(0xFFFFFFFF)
                )
            ) {
                Text("Add")
            }
        }

    }
}
