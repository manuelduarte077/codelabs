package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Volume(
    val unit: String,
    val value: Int
)