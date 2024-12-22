package com.nextvocab.nextvocab.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class MeaningModel(
    val meaning:String,
    val isCheck:Boolean,
    val id: String =  UUID.randomUUID().toString()
) : Parcelable