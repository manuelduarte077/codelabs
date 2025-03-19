package dev.donmanuel.dailyquotes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Data Access Object (DAO) for the Quote entity.
 *
 * This interface defines methods for interacting with the quotes table in the database.
 */

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1")
    fun getRandomQuote(): LiveData<Quote>

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun getAllQuotes(): LiveData<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)
}