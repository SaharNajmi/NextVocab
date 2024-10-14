package com.nextvocab.nextvocab.data.mapper

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition

class WordDefinitionMapper {
    fun toDomainModel(word: WordDefinitionResponse): DomainWordDefinition {
        return DomainWordDefinition(
            word = "${word.word}: ${
                word.phonetic ?: word.phonetics.mapNotNull { it.text }
                    .joinToString()
            }",
            meaning = word.meanings.flatMap { meaning ->
                meaning.definitions.map { definition -> "* ${definition.definition}" }
            },
            example = word.meanings.flatMap { meaning ->
                meaning.definitions.mapNotNull { definition -> definition.example }
                    .map { example -> "+ $example" }
            }
        )
    }
}