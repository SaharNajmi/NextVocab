package com.nextvocab.nextvocab.domain.repository

import com.nextvocab.nextvocab.domain.model.Word

interface DictionaryRepository {
    suspend fun getWordDefinition(word: String): Result<Word>
}