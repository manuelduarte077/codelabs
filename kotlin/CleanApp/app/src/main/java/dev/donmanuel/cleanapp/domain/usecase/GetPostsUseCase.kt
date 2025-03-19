package dev.donmanuel.cleanapp.domain.usecase

import dev.donmanuel.cleanapp.domain.model.Post
import dev.donmanuel.cleanapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<Post> {
        return repository.getPosts()
    }
}