package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.data.remote.DictionaryApiService
import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository

class DictionaryRepositoryImpl(
    private val apiService: DictionaryApiService
) : DictionaryRepository {
    override suspend fun getWordDefinition(word: String): List<WordDefinition> {
        return apiService.getWordDefinition(word).map(WordDefinitionResponse::toDomain)
    }
}