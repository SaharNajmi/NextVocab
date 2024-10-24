package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.data.response.ApiResponse

interface DictionaryRepository {
    suspend fun getWordDefinition(word: String): ApiResponse<WordDefinitionResponse>
}