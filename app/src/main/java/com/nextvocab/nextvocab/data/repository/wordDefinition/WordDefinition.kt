package com.nextvocab.nextvocab.data.repository.wordDefinition

import com.nextvocab.nextvocab.domain.model.ExampleModel
import com.nextvocab.nextvocab.domain.model.MeaningModel

data class WordDefinition(
    val name: String,
    val meaning: List<MeaningModel>,
    val partOfSpeak: String,
    val examples: List<ExampleModel>,
)