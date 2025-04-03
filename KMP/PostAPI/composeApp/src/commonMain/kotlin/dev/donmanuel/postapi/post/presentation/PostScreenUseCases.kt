package dev.donmanuel.postapi.post.presentation

import dev.donmanuel.postapi.post.domain.usecase.GetAllPostsUseCase

data class PostScreenUseCases(
    val allPostsUseCase: GetAllPostsUseCase
)