package com.nextvocab.nextvocab.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainWordDefinition(
    val word: String,
    val meaning: List<String>,
    val partOfSpeak:String,
    val example: List<String>? = null
) : Parcelable