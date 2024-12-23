package com.nextvocab.nextvocab.data.mapper

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel

class WordDefinitionMapper : Mapper<WordDefinitionResponse, DomainWordDefinition> {
    override fun mapToDomain(entity: WordDefinitionResponse): DomainWordDefinition {
        return DomainWordDefinition(
            word = "${entity.word}: ${
                entity.phonetic ?: entity.phonetics.mapNotNull { it.text }
                    .joinToString()
            }",
            meaning = entity.meanings.flatMap { meaning ->
                meaning.definitions.map { definition ->
                    MeaningModel(
                        meaning = definition.definition,
                        isCheck = false
                    )
                }
            },
            partOfSpeak = entity.meanings.joinToString { it.partOfSpeech },
            example = entity.meanings.flatMap { meaning ->
            meaning.definitions.mapNotNull { definition ->
                definition.example?.let {
                    ExampleModel(example = it, isCheck = false)
                }
            }
        }
        )
    }
}