package com.nextvocab.nextvocab.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.presentation.ui.example.ExampleSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.front.FrontSideScreen
import com.nextvocab.nextvocab.presentation.ui.home.HomeScreen
import com.nextvocab.nextvocab.presentation.ui.meaning.MeaningSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.search.SearchScreen
import com.nextvocab.nextvocab.presentation.ui.example.ExampleViewModel
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.detail.CardDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel,
    exampleViewModel: ExampleViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController, sharedViewModel = viewModel)
        }
        composable(route = NavigationItem.Search.route) {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = NavigationItem.FrontSide.route) {
            FrontSideScreen(navController, viewModel)
        }
        composable(
            route = NavigationItem.Meanings.route,
        ) {
            MeaningSelectionScreen(
                navController = navController,
                sharedViewModel = viewModel,
            )
        }

        composable(
            route = NavigationItem.Examples.route,
        ) {
            ExampleSelectionScreen(
                navController = navController,
                sharedViewModel = viewModel,
                exampleViewModel = exampleViewModel
            )
        }
        composable(route = "${NavigationItem.Detail.route}/{item}",
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemJson = backStackEntry.arguments?.getString("item")
            val item = Gson().fromJson(itemJson, WordEntity::class.java)
            CardDetailScreen(navController = navController, item = item)
        }
    }
}