package dev.donmanuel.postapi.di

import dev.donmanuel.postapi.post.PostScreenViewModel
import dev.donmanuel.postapi.post.data.PostRepositoryMemory
import dev.donmanuel.postapi.post.domain.usecase.GetAllPostsUseCase
import dev.donmanuel.postapi.post.presentation.PostScreenUseCases

fun createViewModel(): PostScreenViewModel {
    return PostScreenViewModel(
        PostScreenUseCases(
            GetAllPostsUseCase(
                PostRepositoryMemory()
            )
        )
    )
}