package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Amount(
    val unit: String,
    val value: Int
)