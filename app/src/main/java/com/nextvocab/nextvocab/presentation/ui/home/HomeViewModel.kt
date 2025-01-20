package com.nextvocab.nextvocab.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.data.repository.flashcard.FlashCardsRepository
import com.nextvocab.nextvocab.domain.model.FlashCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val flashCardsRepository: FlashCardsRepository
) :ViewModel(){
    private val _words = MutableStateFlow<List<FlashCard>>(emptyList())
    val words: StateFlow<List<FlashCard>> = _words

    init {
        getWords()
    }

    private fun getWords()  {
        viewModelScope.launch {
            _words.value = flashCardsRepository.getWords()
        }
    }

    fun getTodayReviewWords() = flow<List<FlashCard>> {
        emit(flashCardsRepository.getTodayReviewWords())
    }
}