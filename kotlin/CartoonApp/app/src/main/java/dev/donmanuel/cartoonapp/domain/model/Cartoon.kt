package dev.donmanuel.cartoonapp.domain.model

data class Cartoon(
    val creator: List<String>,
    val episodes: Int,
    val genre: List<String>,
    val id: Int,
    val image: String,
    val rating: String,
    val runtime_in_minutes: Int,
    val title: String,
    val year: Int
)