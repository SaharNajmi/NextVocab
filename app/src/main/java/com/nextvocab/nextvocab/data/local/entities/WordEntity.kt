package com.nextvocab.nextvocab.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "meanings") val meaning: List<String>?,
    @ColumnInfo(name = "partOfSpeak") val partOfSpeak: String,
    @ColumnInfo(name = "examples") val example: List<String>?,
    @ColumnInfo(name = "createdDate") val createdDate: Date = Date(),
)
