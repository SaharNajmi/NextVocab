package com.nextvocab.nextvocab.data.repository.wordDefinition

interface WordDefinitionRepository {
    suspend fun getWordDefinition(word: String): Result<WordDefinition>
}

