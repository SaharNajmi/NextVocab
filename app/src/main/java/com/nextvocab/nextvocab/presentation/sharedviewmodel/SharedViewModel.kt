package com.nextvocab.nextvocab.presentation.sharedviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.response.Loadable
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import com.nextvocab.nextvocab.domain.repository.WordsRepository
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
    private val repository: DictionaryRepository,
    private val wordsRepository: WordsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<Loadable<Word>?>(null)
    val uiState: StateFlow<Loadable<Word>?> = _uiState.asStateFlow()


    private val _wordDefinition = MutableStateFlow<Word?>(null)
    val wordDefinition = _wordDefinition.asStateFlow()

    private val _meanings = ArrayList<String>()
    val meanings: List<String> get() = _meanings

    fun getWords() = flow {
        emit(wordsRepository.getWords())
    }

    fun addMeanings(meanings: List<String>) {
        _meanings.addAll(meanings)
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

    fun insertWord(wordModel: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            wordsRepository.insertWord(wordModel)
        }
    }

    fun updateWord(wordModel: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                wordsRepository.updateWord(wordModel)
                println("Insert succeeded")
            } catch (e: Exception) {
                println("Insert failed: ${e.message}")
            }
        }
    }

    fun deleteWord(wordModel: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                wordsRepository.deleteWord(wordModel)
                println("Insert succeeded")
            } catch (e: Exception) {
                println("Insert failed: ${e.message}")
            }
        }
    }


    fun resetWordDefinition() {
        _uiState.value = Loadable.Canceled
    }
}