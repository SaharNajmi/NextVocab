package com.nextvocab.nextvocab.presentation.navigation

sealed class NavigationItem(val route: String) {
    data object HomeNavigationItem : NavigationItem(Screen.HOME.name)
    data object FrontSideNavigationItem : NavigationItem(Screen.FROND_SIDE_CARD.name)
    data object MeaningNavigationItem : NavigationItem(Screen.MEANING.name)
    data object ExamplesNavigationItem : NavigationItem(Screen.EXAMPLE.name)
    data object SearchNavigationItem : NavigationItem(Screen.SEARCH.name)
}


enum class Screen {
    HOME,
    ADD_CARD,
    FROND_SIDE_CARD,
    MEANING,
    EXAMPLE,
    SEARCH
}
