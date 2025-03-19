package dev.donmanuel.cleanapp.data.repository

import dev.donmanuel.cleanapp.data.api.ApiService
import dev.donmanuel.cleanapp.data.model.toDomain
import dev.donmanuel.cleanapp.domain.model.Post
import dev.donmanuel.cleanapp.domain.repository.PostRepository
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts().map { it.toDomain() }
    }
}