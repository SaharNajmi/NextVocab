package com.nextvocab.nextvocab.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nextvocab.nextvocab.data.local.entities.WordEntity

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    suspend fun getWords(): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertWord(word: WordEntity)

}