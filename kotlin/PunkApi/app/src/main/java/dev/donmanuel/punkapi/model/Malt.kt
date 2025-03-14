package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Malt(
    val amount: AmountX,
    val name: String
)