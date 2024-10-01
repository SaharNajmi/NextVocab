package com.nextvocab.nextvocab.presentation.util

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: String) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()

}
