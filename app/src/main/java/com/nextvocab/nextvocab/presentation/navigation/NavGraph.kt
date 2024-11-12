package com.nextvocab.nextvocab.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.presentation.ui.ExampleSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.FrontSideScreen
import com.nextvocab.nextvocab.presentation.ui.HomeScreen
import com.nextvocab.nextvocab.presentation.ui.MeaningSelectionScreen
import com.nextvocab.nextvocab.presentation.ui.SearchScreen
import com.nextvocab.nextvocab.presentation.viewmodel.WordViewModel
import java.nio.charset.StandardCharsets

@Composable
fun NavGraph(navController: NavHostController, viewModel: WordViewModel) {
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
        composable(route = Screen.FrontSideScreen.route) {
            FrontSideScreen(navController, viewModel)
        }
        composable(
            route = "${Screen.MeaningScreen.route}/{wordModel}",
            arguments = listOf(navArgument("wordModel") { type = NavType.StringType })
        ) { backStackEntry ->
            val wordModelJson = backStackEntry.arguments?.getString("wordModel")
            wordModelJson?.let {
                val decodedJson = try {
                    java.net.URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                } catch (e: Exception) {
                    Log.e("Navigation", "Error decoding JSON", e)
                    null
                }
                decodedJson?.let {
                    val wordModel = Gson().fromJson(it, DomainWordDefinition::class.java)
                    MeaningSelectionScreen(
                        navController = navController,
                        wordModel = wordModel,
                        viewModel = viewModel
                    )
                } ?: Log.e("Navigation", "Decoded JSON is null")
            }
        }

        composable(
            route = "${Screen.ExamplesScreen.route}/{wordModel}",
            arguments = listOf(navArgument("wordModel") { type = NavType.StringType })
        ) { backStackEntry ->
            val wordModelJson = backStackEntry.arguments?.getString("wordModel")
            wordModelJson?.let {
                val decodedJson = try {
                    java.net.URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                } catch (e: Exception) {
                    Log.e("Navigation", "Error decoding JSON", e)
                    null
                }
                decodedJson?.let {
                    val wordModel = Gson().fromJson(it, DomainWordDefinition::class.java)
                    ExampleSelectionScreen(
                        navController = navController,
                        wordModel = wordModel,
                        viewModel = viewModel
                    )
                } ?: Log.e("Navigation", "Decoded JSON is null")
            }
        }
    }
}