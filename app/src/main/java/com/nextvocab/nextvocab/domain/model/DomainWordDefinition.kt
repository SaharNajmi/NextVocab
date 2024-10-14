package com.nextvocab.nextvocab.domain.model

data class DomainWordDefinition(
    val word: String,
    val meaning: List<String>,
    val example: List<String>? = null
)