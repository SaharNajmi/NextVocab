package com.nextvocab.nextvocab.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nextvocab.nextvocab.presentation.ui.AddCardScreen
import com.nextvocab.nextvocab.presentation.ui.SearchScreen
import com.nextvocab.nextvocab.presentation.ui.HomeScreen
import com.nextvocab.nextvocab.presentation.viewmodel.WordViewModel

@Composable
fun NavGraph(navController: NavHostController,viewModel:WordViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
         HomeScreen(navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.AddScreen.route) {
            AddCardScreen()
        }
    }
}