package com.nextvocab.nextvocab.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nextvocab.nextvocab.data.local.entities.WordEntity
import com.nextvocab.nextvocab.data.mapper.Converters


@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}


