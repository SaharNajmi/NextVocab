package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.datasource.remote.DictionaryApiService
import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.data.response.ApiResponse
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository

class DictionaryRepositoryImpl(
    private val apiService: DictionaryApiService
) : DictionaryRepository {
    override suspend fun getWordDefinition(word: String): ApiResponse<WordDefinitionResponse> {
        return try {
            val serviceWordDefinition = apiService.getWordDefinition(word)
            ApiResponse.Success(serviceWordDefinition.get(0))
        } catch (e: Exception) {
            ApiResponse.Error(e.message.toString())
        }
    }
}