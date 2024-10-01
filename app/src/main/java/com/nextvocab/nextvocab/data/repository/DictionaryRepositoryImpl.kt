package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.data.remote.DictionaryApiService
import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import com.nextvocab.nextvocab.presentation.util.ApiResponse

class DictionaryRepositoryImpl(
    private val apiService: DictionaryApiService
) : DictionaryRepository {
    override suspend fun getWordDefinition(word: String):List<WordDefinitionResponse> {
        return try{
            apiService.getWordDefinition(word)
        }
         catch (e: Exception) {
             e.printStackTrace()
             emptyList()
         }
        /*return try {
            val response = apiService.getWordDefinition(word)
            when (response) {
                is ApiResponse.Success -> {
                    val domainList = response.data.map(WordDefinitionResponse::toDomain)
                    ApiResponse.Success(domainList)
                }

                is ApiResponse.Error -> {
                    ApiResponse.Error("Unknown error: " + response.error)

                }

                is ApiResponse.Loading -> {
                    ApiResponse.Loading
                }
            }
        } catch (e: Exception) {
            ApiResponse.Error("Error occurred while fetching data: ${e.localizedMessage}")
        }*/
    }
}