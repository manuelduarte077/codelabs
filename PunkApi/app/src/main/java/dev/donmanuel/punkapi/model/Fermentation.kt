package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Fermentation(
    val temp: Temp
)