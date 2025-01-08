package com.nextvocab.nextvocab.data.response

sealed class Loadable<out T> {
    data class Success<out T>(val data: T) : Loadable<T>()
    data class Error(val error: String) : Loadable<Nothing>()
    data object Loading : Loadable<Nothing>()
}

