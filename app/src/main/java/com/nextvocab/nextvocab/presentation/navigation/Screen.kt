package com.nextvocab.nextvocab.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(NavigationConstants.HOME_ROUTE)
    object AddScreen : Screen(NavigationConstants.ADD_ROUTE)
    object FrontSideScreen : Screen(NavigationConstants.FRONT_SIDE_ROUTE)
    object MeaningScreen : Screen(NavigationConstants.MEANING_ROUTE)
    object DetailScreen : Screen(NavigationConstants.DETAILS_ROUTE)
    object SearchScreen : Screen(NavigationConstants.SEARCH_ROUTE)
}