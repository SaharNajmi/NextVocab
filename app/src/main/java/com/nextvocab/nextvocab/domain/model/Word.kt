package com.nextvocab.nextvocab.domain.model

import com.nextvocab.nextvocab.presentation.ui.study.CardFeedback
import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val name: String,
    val meaning: List<MeaningModel>,
    val partOfSpeak: String,
    val examples: List<ExampleModel>,
    var reviewInterval: Int = 1,
    val feedback: CardFeedback? = null,
    var reviewDate: Long = System.currentTimeMillis()
)
