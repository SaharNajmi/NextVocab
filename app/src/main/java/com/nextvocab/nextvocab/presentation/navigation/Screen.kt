package com.nextvocab.nextvocab.presentation.navigation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen(NavigationConstants.HOME_ROUTE)
    data object AddScreen : Screen(NavigationConstants.ADD_ROUTE)
    data object FrontSideScreen : Screen(NavigationConstants.FRONT_SIDE_ROUTE)
    data object MeaningScreen : Screen(NavigationConstants.MEANING_ROUTE)
    data object ExamplesScreen : Screen(NavigationConstants.EXAMPLES_ROUTE)
    data object DetailScreen : Screen(NavigationConstants.DETAILS_ROUTE)
    data object SearchScreen : Screen(NavigationConstants.SEARCH_ROUTE)
}