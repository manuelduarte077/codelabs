package dev.donmanuel.cartoonapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a favorite cartoon.
 *
 * @property id The unique identifier of the cartoon.
 * @property title The title of the cartoon.
 * @property image The URL or path to the cartoon's image.
 */

@Entity(tableName = "favorites")
data class FavoriteCartoon(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
)