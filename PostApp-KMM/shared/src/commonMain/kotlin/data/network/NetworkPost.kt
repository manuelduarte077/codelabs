package data.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)