package dev.donmanuel.cartoonapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing favorite cartoons in the local database.
 *
 * This interface defines methods for adding, removing, and retrieving favorite cartoons.
 */


@Dao
interface FavoriteCartoonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(cartoonDao: FavoriteCartoon)

    @Delete
    suspend fun removeFavorite(cartoonDao: FavoriteCartoon)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteCartoon>>

}

