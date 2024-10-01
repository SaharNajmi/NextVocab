package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.presentation.util.ApiResponse

interface DictionaryRepository {
    suspend fun getWordDefinition(word: String):List<WordDefinitionResponse>
}