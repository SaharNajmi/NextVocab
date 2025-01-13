package com.nextvocab.nextvocab.presentation.sharedviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.data.response.Loadable
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import com.nextvocab.nextvocab.domain.repository.WordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: DictionaryRepository,
    private val localRepository: WordsRepository
) : ViewModel() {
    init {
        getWords()
    }

    var uiState by mutableStateOf<Loadable<DomainWordDefinition>?>(null)
        private set
    var wordDefinition by mutableStateOf<DomainWordDefinition?>(null)
        private set

    private val _meanings = ArrayList<String>()
    val meanings: List<String> get() = _meanings

    private val _words = mutableStateOf(ArrayList<WordEntity>())
    val words: MutableState<ArrayList<WordEntity>> get() = _words

    fun addMeanings(meanings: List<String>) {
        _meanings.addAll(meanings)
    }

    fun fetchWordDefinition(word: String) {
        uiState = Loadable.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWordDefinition(word).fold(onFailure = {
                uiState = Loadable.Error(it.message ?: "error")
            }, onSuccess = {
                uiState = Loadable.Success(it)
                wordDefinition = it
            })
        }
    }

    private fun getWords() {
        viewModelScope.launch(Dispatchers.IO) {
            _words.value = localRepository.getWords() as ArrayList<WordEntity>
        }
    }

    fun insertWord(wordModel: WordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.insertWord(wordModel)
            getWords()
        }
    }

    fun updateWord(wordModel: WordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                localRepository.updateWord(wordModel)
                getWords()
                println("Insert succeeded")
            } catch (e: Exception) {
                println("Insert failed: ${e.message}")
            }
        }
    }

    fun deleteWord(wordModel: WordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                localRepository.deleteWord(wordModel)
                getWords()
                println("Insert succeeded")
            } catch (e: Exception) {
                println("Insert failed: ${e.message}")
            }
        }
    }

    fun resetWordDefinition() {
        uiState = Loadable.Canceled
    }
}