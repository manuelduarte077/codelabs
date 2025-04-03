package dev.donmanuel.postapi

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.donmanuel.postapi.di.createViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import dev.donmanuel.postapi.post.presentation.PostScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = getViewModel(key = "post-screen-view-model",
            factory = viewModelFactory { createViewModel() })

        PostScreen(
            viewModel = viewModel,
            onPostTapped = { postId ->
                println("Post with id: $postId tapped.")
            }
        )
    }
}