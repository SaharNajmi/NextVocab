package com.nextvocab.nextvocab.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface WordDefinitionService {
    @GET("entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): List<WordDefinitionResponse>
}