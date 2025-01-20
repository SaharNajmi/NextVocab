package com.nextvocab.nextvocab.data.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nextvocab.nextvocab.domain.model.FlashCard

inline fun <reified T> parseJsonToModel(json: String): T? {
    return try {
        Gson().fromJson(json, object : TypeToken<T>() {}.type)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun toDomainModel(json: String): FlashCard? {
    return parseJsonToModel<FlashCard>(json)
}