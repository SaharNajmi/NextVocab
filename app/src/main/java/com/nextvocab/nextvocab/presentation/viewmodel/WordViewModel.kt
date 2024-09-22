package com.nextvocab.nextvocab.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.domain.usecase.GetWordDefinitionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val getWordDefinitionUseCase: GetWordDefinitionUseCase
) : ViewModel() {

    var wordDefinitionState by mutableStateOf<List<WordDefinition>>(emptyList())
        private set

    fun fetchWordDefinition(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            wordDefinitionState = getWordDefinitionUseCase(word)
        }
    }
}