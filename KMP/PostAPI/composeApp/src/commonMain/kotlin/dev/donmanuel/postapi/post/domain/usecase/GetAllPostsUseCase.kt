package dev.donmanuel.postapi.post.domain.usecase

import dev.donmanuel.postapi.core.network.DataError
import dev.donmanuel.postapi.post.domain.model.Post
import dev.donmanuel.postapi.post.domain.model.PostRepository
import dev.donmanuel.postapi.core.Result
import kotlinx.coroutines.flow.first

class GetAllPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Result<List<Post>, DataError> {
        return postRepository.fetchAll().first()
    }
}
