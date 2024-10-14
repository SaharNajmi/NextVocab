package com.nextvocab.nextvocab.domain.usecase

import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetWordDefinitionUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend operator fun invoke(word: String): DomainWordDefinition {
        return repository.getWordDefinition(word)
    }
}