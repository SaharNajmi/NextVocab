package com.nextvocab.nextvocab.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MeaningModel(
    val id: String = UUID.randomUUID().toString(),
    val meaning: String,
    val isCheck: Boolean
)