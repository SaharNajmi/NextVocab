package com.nextvocab.nextvocab.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "meanings") val meaning: List<String>?,
    @ColumnInfo(name = "partOfSpeak") val partOfSpeak: String,
    @ColumnInfo(name = "examples") val example: List<String>?
)
