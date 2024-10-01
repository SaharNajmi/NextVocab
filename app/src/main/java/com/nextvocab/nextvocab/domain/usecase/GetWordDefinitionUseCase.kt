package com.nextvocab.nextvocab.domain.usecase

import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import com.nextvocab.nextvocab.presentation.util.ApiResponse
import javax.inject.Inject

class GetWordDefinitionUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend operator fun invoke(word: String): ApiResponse<List<WordDefinition>> {
        return repository.getWordDefinition(word)
    }
}