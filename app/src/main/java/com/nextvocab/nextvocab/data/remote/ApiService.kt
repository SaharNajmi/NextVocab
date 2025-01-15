package com.nextvocab.nextvocab.data.remote

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): List<WordDefinitionResponse>
}