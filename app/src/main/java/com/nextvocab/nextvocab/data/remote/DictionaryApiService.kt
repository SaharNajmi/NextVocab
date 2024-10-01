package com.nextvocab.nextvocab.data.remote

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.presentation.util.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): ApiResponse<List<WordDefinitionResponse>>
}