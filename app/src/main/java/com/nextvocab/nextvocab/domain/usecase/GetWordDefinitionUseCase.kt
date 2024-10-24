package com.nextvocab.nextvocab.domain.usecase

import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.data.response.ApiResponse
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
import javax.inject.Inject

class GetWordDefinitionUseCase @Inject constructor(
    private val repository: DictionaryRepository,
    private val wordMapper: WordDefinitionMapper
) {
    suspend operator fun invoke(word: String): ApiResponse<DomainWordDefinition> {
        return when (val result = repository.getWordDefinition(word)) {
            is ApiResponse.Success -> ApiResponse.Success(wordMapper.mapToDomain(result.data))
            is ApiResponse.Error -> ApiResponse.Error(result.error)
            ApiResponse.Loading -> ApiResponse.Loading
        }
    }
}