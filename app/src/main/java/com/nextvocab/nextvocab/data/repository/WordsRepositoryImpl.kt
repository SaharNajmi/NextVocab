package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.local.database.WordDao
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.domain.repository.WordsRepository

class WordsRepositoryImpl(private val wordDao: WordDao) : WordsRepository {
    override suspend fun getWords(): List<Word> {
        return wordDao.getWords().map { it.toWord() }
    }

    override suspend fun insertWord(word: Word) {
        wordDao.insertWord(word.toEntity())
    }

    override suspend fun deleteWord(word: Word) {
        wordDao.delete(word.toEntity())
    }

    override suspend fun getWordById(id: String): Word {
        return wordDao.getWordById(id).toWord()
    }

    override suspend fun updateWord(word: Word) {
        wordDao.update(word.toEntity())
    }
}

private fun WordEntity.toWord() = Word(word, meaning?.map { meaning ->
    MeaningModel(
        meaning = meaning, isCheck = false
    )
} ?: emptyList(), partOfSpeak, example?.map { example ->
    ExampleModel(
        example = example, isCheck = false
    )
} ?: emptyList())

private fun Word.toEntity() = with(this) {
    WordEntity(name, meaning.map { it.meaning }, partOfSpeak, examples.map { it.example })
}