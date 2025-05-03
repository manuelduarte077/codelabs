package dev.donmanuel.dailyquotes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.dailyquotes.data.Quote
import dev.donmanuel.dailyquotes.repository.QuoteRepository
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing quotes.
 *
 * This class provides methods to interact with the QuoteRepository and perform CRUD operations on quotes.
 *
 * @property repository The repository instance for accessing the quotes database.
 */

class QuoteViewModel(
    private val repository: QuoteRepository
) : ViewModel() {
    val randomQuote: LiveData<Quote> = repository.getRandomQuote()
    val allQuotes: LiveData<List<Quote>> = repository.getAllQuotes()

    fun addQuote(text: String, author: String) = viewModelScope.launch {
        repository.insertQuote(
            Quote(text = text, author = author)
        )
    }

    fun updateQuote(quote: Quote) = viewModelScope.launch {
        repository.updateQuote(quote)
    }

    fun deleteQuote(quote: Quote) = viewModelScope.launch {
        repository.deleteQuote(quote)
    }
}