package com.nextvocab.nextvocab.data.repository

import com.nextvocab.nextvocab.data.local.database.WordDao
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.data.remote.ApiService
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel
import com.nextvocab.nextvocab.domain.model.Word
import com.nextvocab.nextvocab.domain.repository.WordsRepository
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val wordMapper: WordDefinitionMapper,
    private val wordDao: WordDao
) : WordsRepository {

    override suspend fun getWordDefinition(word: String): Result<Word> {
        return try {
            val serviceWordDefinition = apiService.getWordDefinition(word)
            val domain = wordMapper.mapToDomain(serviceWordDefinition[0])
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getWords(): List<Word> {
        return wordDao.getWords().map { it.toWord() }
    }

    override suspend fun getTodayReviewWords(): List<Word> {
        val dateToday = System.currentTimeMillis()
        return wordDao.getTodayReviewWords(dateToday).map { it.toWord() }
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

private fun WordEntity.toWord() = Word(word,
    meaning?.map { meaning -> MeaningModel(meaning = meaning, isCheck = false) } ?: emptyList(),
    partOfSpeak,
    example?.map { example -> ExampleModel(example = example, isCheck = false) } ?: emptyList(),
    reviewInterval, feedback, reviewDate)

private fun Word.toEntity() = with(this) {
    WordEntity(
        name,
        meaning.map { it.meaning },
        partOfSpeak,
        examples.map { it.example },
        reviewInterval = reviewInterval,
        feedback = feedback,
        reviewDate = reviewDate
    )
}