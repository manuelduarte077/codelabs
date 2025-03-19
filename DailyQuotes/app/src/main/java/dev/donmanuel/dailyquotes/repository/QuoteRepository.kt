package dev.donmanuel.dailyquotes.repository

import androidx.lifecycle.LiveData
import dev.donmanuel.dailyquotes.data.Quote
import dev.donmanuel.dailyquotes.data.QuoteDao

/**
 * Repository class for managing quotes.
 *
 * This class provides methods to interact with the QuoteDao and perform CRUD operations on quotes.
 *
 * @property quoteDao The DAO instance for accessing the quotes database.
 */

class QuoteRepository(private val quoteDao: QuoteDao) {
    fun getRandomQuote(): LiveData<Quote> = quoteDao.getRandomQuote()
    fun getAllQuotes(): LiveData<List<Quote>> = quoteDao.getAllQuotes()
    suspend fun insertQuote(quote: Quote) = quoteDao.insertQuote(quote)
    suspend fun updateQuote(quote: Quote) = quoteDao.updateQuote(quote)
    suspend fun deleteQuote(quote: Quote) = quoteDao.deleteQuote(quote)
}