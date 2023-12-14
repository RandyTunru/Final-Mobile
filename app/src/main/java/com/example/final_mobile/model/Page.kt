package com.example.final_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
    val number: Int
)