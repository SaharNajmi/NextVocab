package com.nextvocab.nextvocab.presentation.sharedviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.response.Loadable
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: DictionaryRepository
) : ViewModel() {

    var uiState by mutableStateOf<Loadable<DomainWordDefinition>?>(null)
        private set
    var wordDefinition by mutableStateOf<DomainWordDefinition?>(null)
        private set

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

    fun resetWordDefinition() {
        uiState = Loadable.Canceled
    }
}