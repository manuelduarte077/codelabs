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

    private val _cartoons = MutableStateFlow<List<Cartoon>>(emptyList())
    val cartoons: StateFlow<List<Cartoon>> = _cartoons

    val favorites: StateFlow<List<FavoriteCartoon>> = repository.getAllFavorites().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    init {
        fetchCartoons2D()
    }

    private fun fetchCartoons2D() {
        viewModelScope.launch {
            _cartoons.value = repository.getCartoons2D()
        }
    }

    fun toggleFavorite(cartoon: Cartoon) {
        viewModelScope.launch {
            val favorite = FavoriteCartoon(
                cartoon.id, cartoon.title, cartoon.image,
            )

            if (favorites.value.any { it.id == cartoon.id }) {
                repository.removeFavorite(favorite)
            } else {
                repository.addFavorite(favorite)
            }
        }
    }
}