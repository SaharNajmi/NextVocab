package com.nextvocab.nextvocab.presentation.ui.example

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nextvocab.nextvocab.domain.model.ExampleModel

class ExampleViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var examples: List<ExampleModel>?
        get() = savedStateHandle["examples"]
        set(value) {
            savedStateHandle["examples"] = value
        }


    fun resetYourSteps() {
        examples = listOf()
    }

}