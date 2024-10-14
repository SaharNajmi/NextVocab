package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.model.WordDefinitionResponse
import com.nextvocab.nextvocab.data.datasource.remote.DictionaryApiService
import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.domain.model.DomainWordDefinition
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository

class DictionaryRepositoryImpl(
    private val apiService: DictionaryApiService
) : DictionaryRepository {
    override suspend fun getWordDefinition(word: String): DomainWordDefinition {
        val serviceWordDefinition = apiService.getWordDefinition(word)
        val mapper = WordDefinitionMapper()
        val domainWordDefinition = mapper.toDomainModel(serviceWordDefinition.get(0))
        return domainWordDefinition
    }
}