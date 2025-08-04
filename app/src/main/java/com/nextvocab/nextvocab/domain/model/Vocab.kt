package com.nextvocab.nextvocab.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocab(
    val id: Int = 0,
    val name: String = "",
    val meaning: String = "test"
) : Parcelable