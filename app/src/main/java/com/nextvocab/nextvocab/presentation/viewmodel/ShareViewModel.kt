package com.nextvocab.nextvocab.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel

class ShareViewModel(private val savedStateHandle: SavedStateHandle) :ViewModel(){
    var meanings by mutableStateOf<List<MeaningModel>>(listOf())
    //var examples by mutableStateOf<List<ExampleModel>>(listOf())
    var examples: List<ExampleModel>?
        get() = savedStateHandle["examples"]
        set(value) { savedStateHandle["examples"] = value }

    fun saveAllData(){

    }

    fun resetYourSteps(){
        meanings= listOf()
        examples= listOf()
    }

}