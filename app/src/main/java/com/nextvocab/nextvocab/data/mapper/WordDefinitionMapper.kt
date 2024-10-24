package com.nextvocab.nextvocab.data.mapper

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition

class WordDefinitionMapper : Mapper<WordDefinitionResponse, DomainWordDefinition> {
    override fun mapToDomain(entity: WordDefinitionResponse): DomainWordDefinition {
        return DomainWordDefinition(
            word = "${entity.word}: ${
                entity.phonetic ?: entity.phonetics.mapNotNull { it.text }
                    .joinToString()
            }",
            meaning = entity.meanings.flatMap { meaning ->
                meaning.definitions.map { definition -> "* ${definition.definition}" }
            },
            example = entity.meanings.flatMap { meaning ->
                meaning.definitions.mapNotNull { definition -> definition.example }
                    .map { example -> "+ $example" }
            }
        )
    }
}