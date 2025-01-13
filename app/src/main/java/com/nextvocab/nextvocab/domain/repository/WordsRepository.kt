package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.data.local.entities.WordEntity

interface WordsRepository {
    suspend fun getWords(): List<WordEntity>
    suspend fun insertWord(word: WordEntity)
    suspend fun deleteWord(word: WordEntity)
    suspend fun updateWord(word: WordEntity)
}