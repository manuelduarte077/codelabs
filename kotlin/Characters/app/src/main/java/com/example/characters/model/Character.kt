package com.example.characters.model

data class Character(
    val created: String = "",
    val episode: List<String> = emptyList(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location,
    val name: String = "",
    val origin: Origin,
    val species: String = "",
    val status: String = "",
    val type: String = "",
    val url: String = ""
)