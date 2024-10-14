package com.nextvocab.nextvocab.data.datasource.remote

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): List<WordDefinitionResponse>
}