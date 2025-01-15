package com.nextvocab.nextvocab.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationItem() {
    @Serializable
    data object Home : NavigationItem()
    @Serializable
    data object FrontSide : NavigationItem()
    @Serializable
    data object Test : NavigationItem()
    @Serializable
    data object Meanings : NavigationItem()
    @Serializable
    data object Examples : NavigationItem()
    @Serializable
    data object Search : NavigationItem()
    @Serializable
    data class Detail(val wordName: String) : NavigationItem()
    @Serializable
    data object Study : NavigationItem()
}