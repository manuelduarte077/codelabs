package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class AmountX(
    val unit: String,
    val value: Double
)