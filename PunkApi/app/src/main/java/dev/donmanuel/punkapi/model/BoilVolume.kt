package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class BoilVolume(
    val unit: String,
    val value: Int
)