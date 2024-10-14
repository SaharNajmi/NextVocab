package com.nextvocab.nextvocab.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.usecase.GetWordDefinitionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val getWordDefinitionUseCase: GetWordDefinitionUseCase
) : ViewModel() {
    var wordDefinition by mutableStateOf<DomainWordDefinition?>(null)
        private set
    fun fetchWordDefinition(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getWordDefinitionUseCase(word)
            wordDefinition = response
//            when (response) {
//                is ApiResponse.Success -> {
//                    wordDefinitionState = response.data
//                }
//                is ApiResponse.Error -> {
////                    _errorMessage.value = response.error
//                }
//                is ApiResponse.Loading -> {
//                }
//            }
        }
    }
}