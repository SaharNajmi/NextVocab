package com.nextvocab.nextvocab.presentation.ui.front

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinition
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionRepository
import com.nextvocab.nextvocab.data.response.Loadable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FrontCardViewModel @Inject constructor(
    private val wordDefinitionRepository: WordDefinitionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<Loadable<WordDefinition>?>(null)
    val uiState: StateFlow<Loadable<WordDefinition>?> = _uiState.asStateFlow()

    private val _wordDefinition = MutableStateFlow<WordDefinition?>(null)
    val wordDefinition = _wordDefinition.asStateFlow()

    fun fetchWordDefinition(word: String) {
        _uiState.value = Loadable.Loading
        viewModelScope.launch(Dispatchers.IO) {
            wordDefinitionRepository.getWordDefinition(word).fold(onFailure = {
                _uiState.value = Loadable.Error(it.message ?: "error")
            }, onSuccess = {
                _uiState.value = Loadable.Success(it)
                _wordDefinition.value = it
            })
        }
    }

}