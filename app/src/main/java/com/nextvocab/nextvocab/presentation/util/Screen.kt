package com.nextvocab.nextvocab.presentation.util

import com.nextvocab.nextvocab.core.Constants

sealed class Screen(val route: String) {
    object MainScreen : Screen(Constants.MainScreen)
    object AddScreen : Screen(Constants.AddScreen)
    object DetailScreen : Screen(Constants.DetailScreen)
}