package dev.donmanuel.cartoonapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.cartoonapp.data.local.FavoriteCartoon
import dev.donmanuel.cartoonapp.domain.model.Cartoon
import dev.donmanuel.cartoonapp.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CartoonViewModel is a ViewModel class that manages the UI-related data for the cartoon app.
 *
 * It interacts with the CartoonRepository to fetch cartoon data and manage favorite cartoons.
 *
 * @param repository The CartoonRepository instance for accessing cartoon data.
 */


@HiltViewModel
class CartoonViewModel @Inject constructor(
    private val repository: CartoonRepository
) : ViewModel() {

    private val _cartoons2D = MutableStateFlow<List<Cartoon>>(emptyList())
    val cartoons2D: StateFlow<List<Cartoon>> = _cartoons2D

    private val _cartoons3D = MutableStateFlow<List<Cartoon>>(emptyList())
    val cartoons3D: StateFlow<List<Cartoon>> = _cartoons3D

    val favorites: StateFlow<List<FavoriteCartoon>> = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    init {
        fetchCartoons2D()
        fetchCartoons3D()
    }

    private fun fetchCartoons2D() {
        viewModelScope.launch {
            _cartoons2D.value = repository.getCartoons2D()
        }
    }

    fun fetchCartoons3D() {
        viewModelScope.launch {
            _cartoons3D.value = repository.getCartoons3D()
        }
    }

    fun removeFavorite(cartoon: FavoriteCartoon) {
        viewModelScope.launch {
            repository.removeFavorite(cartoon)
        }
    }

    fun toggleFavorite(cartoon: Cartoon) {
        viewModelScope.launch {
            val favorite = FavoriteCartoon(cartoon.id, cartoon.title, cartoon.image)

            if (favorites.value.any { it.id == cartoon.id }) {
                repository.removeFavorite(favorite)
            } else {
                repository.addFavorite(favorite)
            }
        }
    }
}