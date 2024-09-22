package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.domain.model.WordDefinition

interface DictionaryRepository {
    suspend fun getWordDefinition(word: String): List<WordDefinition>
}