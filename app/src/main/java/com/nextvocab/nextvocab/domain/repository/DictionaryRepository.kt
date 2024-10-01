package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.presentation.util.ApiResponse

interface DictionaryRepository {
    suspend fun getWordDefinition(word: String): ApiResponse<List<WordDefinition>>
}