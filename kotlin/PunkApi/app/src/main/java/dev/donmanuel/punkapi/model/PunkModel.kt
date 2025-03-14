package dev.donmanuel.punkapi.model

import kotlinx.serialization.Serializable

@Serializable
data class PunkModel(
    val abv: Double = 0.0,
    val attenuation_level: Int = 0,
    val boil_volume: BoilVolume? = null,
    val brewers_tips: String = "",
    val contributed_by: String = "",
    val description: String = "",
    val ebc: Int = 0,
    val first_brewed: String = "",
    val food_pairing: List<String> = emptyList(),
    val ibu: Int = 0,
    val id: Int = 0,
    val image: String = "",
    val ingredients: Ingredients? = null,
    val method: Method? = null,
    val name: String = "",
    val ph: Double = 0.0,
    val srm: Int = 0,
    val tagline: String = "",
    val target_fg: Int = 0,
    val target_og: Int = 0,
    val volume: Volume? = null,
)