package com.nextvocab.nextvocab.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nextvocab.nextvocab.presentation.ui.example.ExampleSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.front.FrontSideScreen
import com.nextvocab.nextvocab.presentation.ui.home.HomeScreen
import com.nextvocab.nextvocab.presentation.ui.meaning.MeaningSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.search.SearchScreen
import com.nextvocab.nextvocab.presentation.ui.example.ExampleViewModel
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel,
    exampleViewModel: ExampleViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.HomeNavigationItem.route
    ) {
        composable(route = NavigationItem.HomeNavigationItem.route) {
            HomeScreen(navController)
        }
        composable(route = NavigationItem.SearchNavigationItem.route) {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = NavigationItem.FrontSideNavigationItem.route) {
            FrontSideScreen(navController, viewModel)
        }
        composable(
            route = NavigationItem.MeaningNavigationItem.route,
        ) {
            MeaningSelectionScreen(
                navController = navController,
                viewModel = viewModel,
                exampleViewModel = exampleViewModel
            )
        }

        composable(
            route = NavigationItem.ExamplesNavigationItem.route,
        ) {
            ExampleSelectionScreen(
                navController = navController,
                viewModel = viewModel,
                exampleViewModel = exampleViewModel
            )
        }
    }
}