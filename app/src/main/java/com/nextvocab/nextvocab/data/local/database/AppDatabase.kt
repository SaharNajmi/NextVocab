package com.nextvocab.nextvocab.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nextvocab.nextvocab.data.local.entities.FlashCardEntity
import com.nextvocab.nextvocab.data.mapper.Converters


@Database(entities = [FlashCardEntity::class], version = 3, exportSchema = true)
@TypeConverters(Converters::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): FlashCardDao
}


