package com.example.final_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val href: String,
    val templated: Boolean?
)
