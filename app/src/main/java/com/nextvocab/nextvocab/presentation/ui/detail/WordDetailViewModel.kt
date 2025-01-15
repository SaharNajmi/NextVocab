package com.nextvocab.nextvocab.presentation.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.domain.repository.WordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(val repository: WordsRepository) : ViewModel(
) {
    private val _word = mutableStateOf<WordEntity?>(null)
    val word: State<WordEntity?> get() = _word

    fun getWordById(id: String) = flow {
        emit(repository.getWordById(id))
    }
}