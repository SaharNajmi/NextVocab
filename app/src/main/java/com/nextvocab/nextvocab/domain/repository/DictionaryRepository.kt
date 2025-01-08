package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.domain.model.DomainWordDefinition

interface DictionaryRepository {
    suspend fun getWordDefinition(word: String): Result<DomainWordDefinition>
}