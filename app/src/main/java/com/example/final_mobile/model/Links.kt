package com.example.final_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val self: Link,
    val next: Link,
    val prev: Link
)