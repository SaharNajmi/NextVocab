package com.nextvocab.nextvocab.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class ExampleModel(
    val id: String = UUID.randomUUID().toString(),
    val example: String,
    val isCheck: Boolean
) : Parcelable