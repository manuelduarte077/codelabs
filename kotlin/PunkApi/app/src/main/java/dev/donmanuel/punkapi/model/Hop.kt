package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Hop(
    val add: String,
    val amount: Amount,
    val attribute: String,
    val name: String
)