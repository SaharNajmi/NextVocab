package com.nextvocab.nextvocab.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ExampleModel(
    val id: String = UUID.randomUUID().toString(),
    val example: String,
    val isCheck: Boolean
)