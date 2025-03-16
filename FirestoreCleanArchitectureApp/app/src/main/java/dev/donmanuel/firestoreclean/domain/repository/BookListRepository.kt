package dev.donmanuel.firestoreclean.domain.repository

import kotlinx.coroutines.flow.Flow
import dev.donmanuel.firestoreclean.domain.model.Book
import dev.donmanuel.firestoreclean.domain.model.Response

typealias BookListResponse = Response<List<Book>>
typealias AddBookResponse = Response<Unit>
typealias UpdateBookResponse = Response<Unit>
typealias DeleteBookResponse = Response<Unit>

interface BookListRepository {
    fun getBookList(): Flow<BookListResponse>

    suspend fun addBook(book: Map<String, String>): AddBookResponse

    suspend fun updateBook(bookUpdates: Map<String, String>): UpdateBookResponse

    suspend fun deleteBook(bookId: String): DeleteBookResponse
}