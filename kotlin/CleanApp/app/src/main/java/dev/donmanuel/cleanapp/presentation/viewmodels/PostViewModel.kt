package dev.donmanuel.cleanapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.cleanapp.domain.model.Post
import dev.donmanuel.cleanapp.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
) : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList()) // Initialize with an empty list
    val posts: StateFlow<List<Post>> get() = _posts // Expose the posts as a StateFlow

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _posts.value = getPostsUseCase() // Fetch posts from the use case
        }
    }
}