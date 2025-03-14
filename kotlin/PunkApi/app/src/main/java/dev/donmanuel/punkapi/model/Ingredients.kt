package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable


@Serializable
data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)