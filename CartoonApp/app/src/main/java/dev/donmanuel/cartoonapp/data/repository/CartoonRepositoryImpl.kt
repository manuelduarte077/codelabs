package dev.donmanuel.cartoonapp.data.repository

import dev.donmanuel.cartoonapp.data.local.FavoriteCartoon
import dev.donmanuel.cartoonapp.data.local.FavoriteCartoonDao
import dev.donmanuel.cartoonapp.data.remote.CartoonApi
import dev.donmanuel.cartoonapp.domain.model.Cartoon
import dev.donmanuel.cartoonapp.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * CartoonRepositoryImpl is an implementation of the CartoonRepository interface.
 *
 * It provides methods to fetch cartoon data from a remote API and manage favorite cartoons
 * in a local database.
 *
 * @param api The CartoonApi instance for making network requests.
 * @param dao The FavoriteCartoonDao instance for accessing the local database.
 */

class CartoonRepositoryImpl @Inject constructor(
    private val api: CartoonApi,
    private val dao: FavoriteCartoonDao
) : CartoonRepository {
    override suspend fun getCartoons2D(): List<Cartoon> {
        return api.getCartoons2D()
    }

    override suspend fun getCartoons3D(): List<Cartoon> {
        return api.getCartoons3D()
    }

    override fun getAllFavorites(): Flow<List<FavoriteCartoon>> {
        return dao.getAllFavorites()
    }

    override suspend fun addFavorite(cartoonDao: FavoriteCartoon) {
        dao.addFavorite(cartoonDao)
    }

    override suspend fun removeFavorite(cartoonDao: FavoriteCartoon) {
        dao.removeFavorite(cartoonDao)
    }

}