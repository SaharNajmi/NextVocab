package com.nextvocab.nextvocab.presentation.ui.study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.domain.repository.WordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(val repository: WordsRepository) : ViewModel() {

    fun getTodayReviewWords()= flow<List<Word>> {
        emit(repository.getTodayReviewWords())
    }

    fun updateWord(wordModel: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateWord(wordModel)
                println("update succeeded")
            } catch (e: Exception) {
                println("update failed: ${e.message}")
            }
        }
    }
}