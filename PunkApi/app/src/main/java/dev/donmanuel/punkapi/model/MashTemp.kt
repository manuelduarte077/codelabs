package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class MashTemp(
    val duration: Int,
    val temp: Temp
)