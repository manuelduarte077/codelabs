package dev.donmanuel.dailyquotes.di

import dev.donmanuel.dailyquotes.data.QuoteDatabase
import dev.donmanuel.dailyquotes.repository.QuoteRepository
import dev.donmanuel.dailyquotes.viewmodels.QuoteViewModel
import org.koin.dsl.module

/**
 * Koin module for dependency injection.
 * This module provides the necessary dependencies for the application.
 */

val appModule = module {
    single { QuoteDatabase.getInstance(get()).quoteDao() } // Provides the QuoteDao instance
    single { QuoteRepository(get()) } // Provides the QuoteRepository instance
    // ViewModel injection
    single { QuoteViewModel(get()) }
}

