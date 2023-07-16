package com.thiago.dragonballzapp.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class ApiResponse(
    val success:Boolean,
    val message: String?=null,
    val prevPage: Int? =null,
    val nextPage: Int? =null,
    val heroes: List<Hero> = emptyList(),
    val lastUpdated: Long? = null
)
