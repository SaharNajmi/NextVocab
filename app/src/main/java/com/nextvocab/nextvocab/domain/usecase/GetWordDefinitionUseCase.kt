package com.nextvocab.nextvocab.domain.usecase

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.domain.model.WordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import com.nextvocab.nextvocab.presentation.util.ApiResponse
import javax.inject.Inject

class GetWordDefinitionUseCase @Inject constructor(private val repository: DictionaryRepository) {
    suspend operator fun invoke(word: String): List<WordDefinitionResponse>{
        return repository.getWordDefinition(word)
    }
}