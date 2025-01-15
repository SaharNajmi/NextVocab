package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.domain.model.Word

interface WordsRepository {
    suspend fun getWords(): List<Word>
    suspend fun insertWord(word: Word)
    suspend fun deleteWord(word: Word)
    suspend fun getWordById(id: String):Word
    suspend fun updateWord(word: Word)
}




