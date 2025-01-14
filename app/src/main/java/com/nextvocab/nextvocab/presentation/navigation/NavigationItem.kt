package com.nextvocab.nextvocab.presentation.navigation

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object FrontSide : NavigationItem(Screen.FROND_SIDE_CARD.name)
    data object Meanings : NavigationItem(Screen.MEANING.name)
    data object Examples : NavigationItem(Screen.EXAMPLE.name)
    data object Search : NavigationItem(Screen.SEARCH.name)
    data object Detail : NavigationItem(Screen.DETAIL.name)
    data object Study : NavigationItem(Screen.STUDY.name)
}


enum class Screen {
    HOME,
    FROND_SIDE_CARD,
    MEANING,
    EXAMPLE,
    SEARCH,
    DETAIL,
    STUDY
}
