package com.nextvocab.nextvocab.data.mapper

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel

class WordDefinitionMapper : Mapper<WordDefinitionResponse, Word> {
    override fun mapToDomain(entity: WordDefinitionResponse): Word {
        return Word(

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