package com.nextvocab.nextvocab.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nextvocab.nextvocab.data.local.entities.FlashCardEntity

@Dao
interface FlashCardDao {
    @Query("SELECT * FROM words ORDER BY createdDate DESC")
    suspend fun getWords(): List<FlashCardEntity>

    @Query("SELECT * FROM words WHERE reviewDate <= :dateToday")
    suspend fun getTodayReviewWords(dateToday: Long): List<FlashCardEntity>

    @Query("SELECT * FROM words WHERE word= :word")
    suspend fun getWordById(word: String): FlashCardEntity

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertWord(word: FlashCardEntity)

    @Delete
    suspend fun delete(word: FlashCardEntity)

    @Update
    suspend fun update(word: FlashCardEntity)
}