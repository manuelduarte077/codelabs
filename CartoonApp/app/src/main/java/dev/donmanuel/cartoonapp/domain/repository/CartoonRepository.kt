package dev.donmanuel.cartoonapp.domain.repository

import dev.donmanuel.cartoonapp.data.local.FavoriteCartoon
import dev.donmanuel.cartoonapp.domain.model.Cartoon
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing cartoon data.
 *
 * This interface defines methods for fetching cartoons from a remote API and managing favorite cartoons
 * in a local database.
 */


interface CartoonRepository {
    /// Remote
    suspend fun getCartoons2D(): List<Cartoon>
    suspend fun getCartoons3D(): List<Cartoon>

    /// Local
    fun getAllFavorites(): Flow<List<FavoriteCartoon>>
    suspend fun addFavorite(cartoon: FavoriteCartoon)
    suspend fun removeFavorite(cartoon: FavoriteCartoon)
}