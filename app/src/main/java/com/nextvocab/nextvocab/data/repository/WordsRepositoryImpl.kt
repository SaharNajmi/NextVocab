package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.local.database.WordDao
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.domain.repository.WordsRepository

class WordsRepositoryImpl(private val wordDao: WordDao) : WordsRepository {
    override suspend fun getWords(): List<WordEntity> {
        return wordDao.getWords()
    }

    override suspend fun insertWord(word: WordEntity) {
        wordDao.insertWord(word)
    }
}