package com.nextvocab.nextvocab.domain.usecase

import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetWordDefinitionUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend operator fun invoke(word: String): List<WordDefinition> {
        return repository.getWordDefinition(word)
    }
}