package com.nextvocab.nextvocab.data.repository.wordDefinition

import com.nextvocab.nextvocab.data.mapper.Mapper
import com.nextvocab.nextvocab.data.remote.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel

class WordDefinitionMapper : Mapper<WordDefinitionResponse, WordDefinition> {

    override fun mapToDomain(entity: WordDefinitionResponse): WordDefinition {
        return WordDefinition(

            name = "${entity.word}: ${
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
            examples = entity.meanings.flatMap { meaning ->
                meaning.definitions.mapNotNull { definition ->
                    definition.example?.let {
                        ExampleModel(example = it, isCheck = false)
                    }
                }
            }
        )
    }
}