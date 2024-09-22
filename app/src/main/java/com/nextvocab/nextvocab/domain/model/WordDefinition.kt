package com.nextvocab.nextvocab.domain.model

data class WordDefinition(
    val word: String,
    val meaning: String,
    val example: String? = null
)