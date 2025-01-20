package com.nextvocab.nextvocab.data.repository.wordDefinition

import com.nextvocab.nextvocab.data.remote.WordDefinitionService

class WordDefinitionRepositoryImpl(
    private val wordDefinitionService: WordDefinitionService,
    private val wordDefinitionMapper: WordDefinitionMapper
) : WordDefinitionRepository {

    override suspend fun getWordDefinition(word: String): Result<WordDefinition> {
        return try {
            val serviceWordDefinition = wordDefinitionService.getWordDefinition(word)
            val domain = wordDefinitionMapper.mapToDomain(serviceWordDefinition[0])
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}