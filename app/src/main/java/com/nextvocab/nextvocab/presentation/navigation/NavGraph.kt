package com.nextvocab.nextvocab.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.detail.CardDetailScreen
import com.nextvocab.nextvocab.presentation.ui.detail.WordDetailViewModel
import com.nextvocab.nextvocab.presentation.ui.example.ExampleSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.example.ExampleViewModel
import com.nextvocab.nextvocab.presentation.ui.front.FrontSideScreen
import com.nextvocab.nextvocab.presentation.ui.home.HomeScreen
import com.nextvocab.nextvocab.presentation.ui.meaning.MeaningSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.search.SearchScreen
import com.nextvocab.nextvocab.presentation.ui.searchb.SearchScreenTest
import com.nextvocab.nextvocab.presentation.ui.study.Study

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel,
    detailViewModel: WordDetailViewModel,
    exampleViewModel: ExampleViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home
    ) {
        composable<NavigationItem.Home> {
            HomeScreen(sharedViewModel = viewModel(LocalContext.current as ComponentActivity),
                onStudyClick = {
                    navController.navigate(NavigationItem.Study)
                },
                addNewCardClick = {
                    navController.navigate(NavigationItem.FrontSide)
                }
            ) { wordId ->
                val route = NavigationItem.Detail(wordId)
                navController.navigate(route)
            }
        }
        composable<NavigationItem.Search> {
            SearchScreen(
                navController = navController,
                viewModel = viewModel(LocalContext.current as ComponentActivity)
            )
        }
        composable<NavigationItem.FrontSide> {
            FrontSideScreen(navController, viewModel)
        }
        composable<NavigationItem.Test> {
            SearchScreenTest()
        }
        composable<NavigationItem.Study> {
            Study(
                navController = navController,
                sharedViewModel = viewModel
            )
        }
        composable<NavigationItem.Meanings> {
            MeaningSelectionScreen(
                navController = navController,
                sharedViewModel =viewModel
            )
        }

        composable<NavigationItem.Examples> {
            ExampleSelectionScreen(
                navController = navController,
                sharedViewModel = viewModel(LocalContext.current as ComponentActivity),
                exampleViewModel = exampleViewModel
            )
        }

        composable<NavigationItem.Detail> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationItem.Detail>()
            CardDetailScreen(
                navController = navController,
                wordName = route.wordName,
                sharedViewModel = viewModel,
                detailViewModel=detailViewModel
            )
        }
    }
}