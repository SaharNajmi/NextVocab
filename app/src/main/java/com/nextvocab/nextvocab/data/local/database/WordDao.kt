package com.nextvocab.nextvocab.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nextvocab.nextvocab.data.local.entities.WordEntity

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    suspend fun getWords(): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertWord(word: WordEntity)

    @Delete
    suspend fun delete(word: WordEntity)

    @Update
    suspend fun update(word: WordEntity)
}