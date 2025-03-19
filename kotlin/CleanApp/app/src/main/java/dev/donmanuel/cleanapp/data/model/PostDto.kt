package dev.donmanuel.cleanapp.data.model

import dev.donmanuel.cleanapp.domain.model.Post

data class PostDto(
    val id: Int,
    val title: String,
    val body: String,
)


fun PostDto.toDomain(): Post {
    return Post(id, title, body)
}