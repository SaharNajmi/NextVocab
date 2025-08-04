package com.nextvocab.nextvocab.data.remote


data class WordDefinitionResponse(
    val license: License,
    val meanings: List<Meaning>,
    val phonetic: String?,
    val phonetics: List<Phonetic>,
    val sourceUrls: List<String>,
    val word: String
) {

    data class Meaning(
        val definitions: List<Definition>,
        val partOfSpeech: String,
        val synonyms: List<String> = emptyList(),
        val antonyms: List<String> = emptyList()
    )

    data class License(
        val name: String,
        val url: String
    )

    data class Phonetic(
        val audio: String,
        val license: License?,
        val sourceUrl: String?,
        val text: String?
    )

    data class Definition(
        val definition: String,
        val synonyms: List<String> = emptyList(),
        val antonyms: List<String> = emptyList(),
        val example: String? = null
    )

    data class ErrorResponse(
        val title: String,
        val message: String,
        val resolution: String
    )
}
