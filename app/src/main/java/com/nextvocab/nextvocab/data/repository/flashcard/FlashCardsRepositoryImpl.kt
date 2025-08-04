package com.nextvocab.nextvocab.data.repository.flashcard

import com.nextvocab.nextvocab.data.local.database.FlashCardDao
import com.nextvocab.nextvocab.data.local.entities.FlashCardEntity
import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.FlashCard
import com.nextvocab.nextvocab.domain.model.MeaningModel
import javax.inject.Inject

class FlashCardsRepositoryImpl @Inject constructor(
    private val flashCardDao: FlashCardDao
) : FlashCardsRepository {

    override suspend fun getWords(): List<FlashCard> {
        return flashCardDao.getWords().map { it.toWord() }
    }

    override suspend fun getTodayReviewWords(): List<FlashCard> {
        val dateToday = System.currentTimeMillis()
        return flashCardDao.getTodayReviewWords(dateToday).map { it.toWord() }
    }

    override suspend fun insertWord(flashCard: FlashCard) {
        flashCardDao.insertWord(flashCard.toEntity())
    }

    override suspend fun deleteWord(flashCard: FlashCard) {
        flashCardDao.delete(flashCard.toEntity())
    }

    override suspend fun getWordById(id: String): FlashCard {
        return flashCardDao.getWordById(id).toWord()
    }

    override suspend fun updateWord(flashCard: FlashCard) {
        flashCardDao.update(flashCard.toEntity())
    }
}

private fun FlashCardEntity.toWord() = FlashCard(word,
    meaning?.map { meaning -> MeaningModel(meaning = meaning, isCheck = false) } ?: emptyList(),
    partOfSpeak,
    example?.map { example -> ExampleModel(example = example, isCheck = false) } ?: emptyList(),
    reviewInterval, feedback, reviewDate)

private fun FlashCard.toEntity() = with(this) {
    FlashCardEntity(
        name,
        meaning.map { it.meaning },
        partOfSpeak,
        examples.map { it.example },
        reviewInterval = reviewInterval,
        feedback = feedback,
        reviewDate = reviewDate
    )
}