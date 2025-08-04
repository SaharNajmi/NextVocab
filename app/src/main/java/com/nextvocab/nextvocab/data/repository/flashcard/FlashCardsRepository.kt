package com.nextvocab.nextvocab.data.repository.flashcard

import com.nextvocab.nextvocab.domain.model.FlashCard

interface FlashCardsRepository {
    suspend fun getWords(): List<FlashCard>
    suspend fun getTodayReviewWords(): List<FlashCard>
    suspend fun insertWord(flashCard: FlashCard)
    suspend fun deleteWord(flashCard: FlashCard)
    suspend fun getWordById(id: String): FlashCard
    suspend fun updateWord(flashCard: FlashCard)
}




