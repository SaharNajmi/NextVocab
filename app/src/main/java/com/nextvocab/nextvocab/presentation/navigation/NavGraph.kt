package com.nextvocab.nextvocab.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nextvocab.nextvocab.presentation.sharedviewmodel.SharedViewModel
import com.nextvocab.nextvocab.presentation.ui.detail.FlashCardDetailScreen
import com.nextvocab.nextvocab.presentation.ui.detail.FlashCardDetailViewModel
import com.nextvocab.nextvocab.presentation.ui.example.ExamplesScreen
import com.nextvocab.nextvocab.presentation.ui.example.ExampleViewModel
import com.nextvocab.nextvocab.presentation.ui.front.FrontSideScreen
import com.nextvocab.nextvocab.presentation.ui.home.HomeScreen
import com.nextvocab.nextvocab.presentation.ui.home.HomeViewModel
import com.nextvocab.nextvocab.presentation.ui.meaning.MeaningsScreen
import com.nextvocab.nextvocab.presentation.ui.study.Study
import com.nextvocab.nextvocab.presentation.ui.study.StudyViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    detailViewModel: FlashCardDetailViewModel,
    exampleViewModel: ExampleViewModel,
    studyViewModel: StudyViewModel,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home
    ) {
        composable<NavigationItem.Home> {
            HomeScreen(homeViewModel = homeViewModel,
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
            FrontSideScreen(
                sharedViewModel = sharedViewModel,
                onMeaningScreen = { navController.navigate(NavigationItem.Meanings) },
                onCancelClick = { navController.navigate(NavigationItem.Home) })
        }

        composable<NavigationItem.Study> {
            Study(
                viewModel = studyViewModel,
                onBackClick = { navController.navigate(NavigationItem.Home) }
            )
        }

        composable<NavigationItem.Meanings> {
            MeaningsScreen(
                sharedViewModel = sharedViewModel,
                onExampleScreen = {
                    navController.navigate(NavigationItem.Examples)
                },
                onCancelClick = { navController.navigate(NavigationItem.Home) }
            )
        }

        composable<NavigationItem.Examples> {
            ExamplesScreen(
                sharedViewModel = sharedViewModel,
                exampleViewModel = exampleViewModel,
                onBackClick = { navController.popBackStack() },
                onCancelClick = { navController.navigate(NavigationItem.Home) }
            )
        }

        composable<NavigationItem.Detail> { backStackEntry ->
            val route = backStackEntry.toRoute<NavigationItem.Detail>()
            FlashCardDetailScreen(
                wordName = route.wordName,
                viewModel = detailViewModel,
                goHome = { navController.navigate(NavigationItem.Home) }
            )
        }
    }
}
