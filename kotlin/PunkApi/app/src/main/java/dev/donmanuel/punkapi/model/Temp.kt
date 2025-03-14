package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Temp(
    val unit: String,
    val value: Int
)