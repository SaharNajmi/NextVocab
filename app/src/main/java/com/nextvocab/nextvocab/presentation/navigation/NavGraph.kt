package com.nextvocab.nextvocab.presentation.navigation

import androidx.compose.runtime.Composable
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
import com.nextvocab.nextvocab.presentation.ui.study.Study
import com.nextvocab.nextvocab.presentation.ui.study.StudyViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel,
    detailViewModel: WordDetailViewModel,
    exampleViewModel: ExampleViewModel,
    studyViewModel: StudyViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home
    ) {
        composable<NavigationItem.Home> {
            HomeScreen(sharedViewModel = viewModel,
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

        composable<NavigationItem.FrontSide> {
            FrontSideScreen(navController, viewModel)
        }

        composable<NavigationItem.Study> {
            Study(
                viewModel = studyViewModel,
                onBackClick = { navController.navigate(NavigationItem.Home) }
            )
        }
        composable<NavigationItem.Meanings> {
            MeaningSelectionScreen(
                navController = navController,
                sharedViewModel = viewModel
            )
        }

        composable<NavigationItem.Examples> {
            ExampleSelectionScreen(
                sharedViewModel = viewModel,
                exampleViewModel = exampleViewModel,
                onBackClick = { navController.popBackStack() },
                onCancelClick = { navController.navigate(NavigationItem.Home) }
            )
        }

        composable<NavigationItem.Detail> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationItem.Detail>()
            CardDetailScreen(
                wordName = route.wordName,
                sharedViewModel = viewModel,
                detailViewModel = detailViewModel,
                goHome = { navController.navigate(NavigationItem.Home) }
            )
        }
    }
}