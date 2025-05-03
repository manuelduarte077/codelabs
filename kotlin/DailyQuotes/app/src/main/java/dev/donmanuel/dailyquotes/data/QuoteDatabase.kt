package dev.donmanuel.dailyquotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
    * QuoteDatabase.kt
    * This file contains the implementation of the QuoteDatabase class, which is a Room database
    * for storing quotes. It includes the database version, export schema option, and the DAO for accessing the database.
    * The QuoteDatabase class is a singleton, ensuring that only one instance of the database exists
    * throughout the application.
 */

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java, "quote_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}