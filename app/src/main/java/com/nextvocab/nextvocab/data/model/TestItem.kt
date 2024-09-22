package com.nextvocab.nextvocab.data.model

import com.nextvocab.nextvocab.domain.model.WordDefinition


data class TestItem(
    val word: String,
    val meanings: List<MeaningResponse>
) {
    fun toDomain(): WordDefinition {
        return WordDefinition(
            word = word,
            meaning = meanings.joinToString(", ") { it.definition },
            example = meanings.firstOrNull()?.example
        )
    }
}

data class MeaningResponse(
    val definition: String,
    val example: String? = null
)