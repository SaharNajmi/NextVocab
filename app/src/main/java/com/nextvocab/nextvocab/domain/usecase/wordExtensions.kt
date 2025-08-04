package com.nextvocab.nextvocab.domain.usecase

import com.nextvocab.nextvocab.domain.model.FlashCard

fun List<FlashCard>.filterByName(name: String): List<FlashCard> {
    return if (name.isEmpty()) {
        this
    } else {
        this.filter { it.name.contains(name) }
    }
}