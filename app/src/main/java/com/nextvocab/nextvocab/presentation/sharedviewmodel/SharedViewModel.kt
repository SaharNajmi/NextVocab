package com.nextvocab.nextvocab.presentation.sharedviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.response.Loadable
import com.nextvocab.nextvocab.domain.model.FlashCard
import com.nextvocab.nextvocab.data.repository.flashcard.FlashCardsRepository
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinition
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: WordDefinitionRepository,
    private val flashCardsRepository: FlashCardsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<Loadable<WordDefinition>?>(null)
    val uiState: StateFlow<Loadable<WordDefinition>?> = _uiState.asStateFlow()

    private val _wordDefinition = MutableStateFlow<WordDefinition?>(null)
    val wordDefinition = _wordDefinition.asStateFlow()


    private val _meanings = ArrayList<String>()
    val meanings: List<String> get() = _meanings

    fun insertWord(flashCardModel: FlashCard) {
        viewModelScope.launch(Dispatchers.IO) {
            flashCardsRepository.insertWord(flashCardModel)
        }
    }

    fun fetchWordDefinition(word: String) {
        _uiState.value = Loadable.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWordDefinition(word).fold(onFailure = {
                _uiState.value = Loadable.Error(it.message ?: "error")
            }, onSuccess = {
                _uiState.value = Loadable.Success(it)
                _wordDefinition.value = it
            })
        }
    }

    fun addMeanings(meanings: List<String>) {
        _meanings.addAll(meanings)
    }


    fun resetWordDefinition() {
        _uiState.value = Loadable.Canceled
    }
}