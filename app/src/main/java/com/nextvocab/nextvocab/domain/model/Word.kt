package com.nextvocab.nextvocab.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Word(
    val name: String,
    val meaning: List<MeaningModel>,
    val partOfSpeak: String,
    val examples: List<ExampleModel>
)