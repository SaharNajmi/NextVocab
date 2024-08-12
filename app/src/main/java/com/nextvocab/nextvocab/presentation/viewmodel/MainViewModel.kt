package com.nextvocab.nextvocab.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class MainViewModel : ViewModel() {
    private val _count = mutableStateOf(0)
    val count: State<Int> = _count

    fun incrementCount() {
        _count.value += 1
    }

    fun decrementCount() {
        _count.value -= 1
    }
}