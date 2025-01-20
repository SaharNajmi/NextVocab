package com.nextvocab.nextvocab.presentation.ui.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.domain.model.FlashCard
import com.nextvocab.nextvocab.data.repository.flashcard.FlashCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(val repository: FlashCardsRepository) : ViewModel() {

    fun getTodayReviewWords()= flow<List<FlashCard>> {
        emit(repository.getTodayReviewWords())
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
}