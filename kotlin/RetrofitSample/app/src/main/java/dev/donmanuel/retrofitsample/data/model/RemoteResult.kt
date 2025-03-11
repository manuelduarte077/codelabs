package dev.donmanuel.retrofitsample.data.model

data class RemoteResult(
    val page: Int,
    val results: List<MovieDiscover>,
    val total_pages: Int,
    val total_results: Int
)