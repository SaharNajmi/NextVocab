package com.nextvocab.nextvocab.data.mapper

interface Mapper<E, D> {
    fun mapToDomain(entity: E): D
//    fun mapToEntity(domain: D): E
}