package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.remote.DictionaryApiService
import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository

class DictionaryRepositoryImpl(
    private val apiService: DictionaryApiService,
    private val wordMapper: WordDefinitionMapper
) : DictionaryRepository {
    override suspend fun getWordDefinition(word: String): Result<Word> {
        return try {
            val serviceWordDefinition = apiService.getWordDefinition(word)
            val domain = wordMapper.mapToDomain(serviceWordDefinition[0])
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}