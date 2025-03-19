package dev.donmanuel.cleanapp.domain.repository

import dev.donmanuel.cleanapp.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}