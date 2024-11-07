package com.nextvocab.nextvocab.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DomainWordDefinition(
    val word: String,
    val meaning: List<String>,
    val example: List<String>? = null
)