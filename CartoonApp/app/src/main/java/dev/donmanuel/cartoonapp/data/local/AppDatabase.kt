package dev.donmanuel.cartoonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * AppDatabase is the main database class for the application.
 * It extends RoomDatabase and provides access to the DAO.
 */

@Database(entities = [FavoriteCartoon::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCartoonDao(): FavoriteCartoonDao
}