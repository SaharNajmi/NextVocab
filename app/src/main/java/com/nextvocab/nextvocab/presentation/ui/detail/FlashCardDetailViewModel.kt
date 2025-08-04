package com.nextvocab.nextvocab.presentation.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.local.entities.FlashCardEntity
import com.nextvocab.nextvocab.data.repository.flashcard.FlashCardsRepository
import com.nextvocab.nextvocab.domain.model.FlashCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashCardDetailViewModel @Inject constructor(val repository: FlashCardsRepository) : ViewModel(
) {
    private val _word = mutableStateOf<FlashCardEntity?>(null)
    val word: State<FlashCardEntity?> get() = _word

    fun getWordById(id: String) = flow {
        emit(repository.getWordById(id))
    }



    fun updateWord(flashCardModel: FlashCard) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateWord(flashCardModel)
                println("update succeeded")
            } catch (e: Exception) {
                println("update failed: ${e.message}")
            }
        }
    }

    fun deleteWord(flashCardModel: FlashCard) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteWord(flashCardModel)
                println("Insert succeeded")
            } catch (e: Exception) {
                println("Insert failed: ${e.message}")
            }
        }
    }
}