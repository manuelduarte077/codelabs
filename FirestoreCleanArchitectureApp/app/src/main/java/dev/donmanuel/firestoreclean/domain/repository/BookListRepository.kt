package dev.donmanuel.firestoreclean.domain.repository

import kotlinx.coroutines.flow.Flow
import dev.donmanuel.firestoreclean.domain.model.Book
import dev.donmanuel.firestoreclean.domain.model.Response

typealias BookListResponse = Response<List<Book>>
typealias AddBookResponse = Response<Unit>
typealias UpdateBookResponse = Response<Unit>
typealias DeleteBookResponse = Response<Unit>

/**
 * Interfaz que define las operaciones de repositorio para la lista de libros.
 */
interface BookListRepository {
    /**
     * Obtiene una lista de libros como un flujo de [BookListResponse].
     *
     * @return Un [Flow] que emite [BookListResponse], que puede ser un éxito con una lista de libros o un error.
     */
    fun getBookList(): Flow<BookListResponse>

    /**
     * Agrega un libro al repositorio.
     *
     * @param book Un mapa que representa el libro a agregar. Las claves representan los campos del libro.
     * @return Un [AddBookResponse] que indica el resultado de la operación, que puede ser un éxito o un error.
     */
    suspend fun addBook(book: Map<String, String>): AddBookResponse

    /**
     * Actualiza un libro en el repositorio.
     *
     * @param bookUpdates Un mapa que representa las actualizaciones del libro. Las claves representan los campos a actualizar.
     * @return Un [UpdateBookResponse] que indica el resultado de la operación, que puede ser un éxito o un error.
     */
    suspend fun updateBook(bookUpdates: Map<String, String>): UpdateBookResponse

    /**
     * Elimina un libro del repositorio.
     */
    suspend fun deleteBook(bookId: String): DeleteBookResponse
}