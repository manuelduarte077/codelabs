package dev.donmanuel.firestoreclean.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import dev.donmanuel.firestoreclean.core.AUTHOR_FIELD
import dev.donmanuel.firestoreclean.core.ID_FIELD
import dev.donmanuel.firestoreclean.core.TITLE_FIELD
import dev.donmanuel.firestoreclean.domain.model.Book
import dev.donmanuel.firestoreclean.domain.model.Response
import dev.donmanuel.firestoreclean.domain.repository.BookListRepository

class BookListRepositoryImpl(
    private val booksRef: CollectionReference
) : BookListRepository {
    override fun getBookList() = callbackFlow {
        val listener = booksRef.orderBy(TITLE_FIELD).addSnapshotListener { bookListSnapshot, e ->
            val bookListResponse = if (bookListSnapshot != null) {
                val bookList = bookListSnapshot.map { bookSnapshot ->
                    bookSnapshot.toBook()
                }
                Response.Success(bookList)
            } else {
                Response.Failure(e)
            }
            trySend(bookListResponse)
        }
        awaitClose {
            listener.remove()
        }
    }

    override suspend fun addBook(book: Map<String, String>) = try {
        booksRef.add(book).await()
        Response.Success(Unit)
    } catch (e: Exception) {
        Response.Failure(e)
    }

    override suspend fun updateBook(bookUpdates: Map<String, String>) = try {
        val bookId = bookUpdates.getValue(ID_FIELD)
        booksRef.document(bookId).update(bookUpdates).await()
        Response.Success(Unit)
    } catch (e: Exception) {
        Response.Failure(e)
    }

    override suspend fun deleteBook(bookId: String) = try {
        booksRef.document(bookId).delete().await()
        Response.Success(Unit)
    } catch (e: Exception) {
        Response.Failure(e)
    }
}

fun DocumentSnapshot.toBook() = Book(
    author = getString(AUTHOR_FIELD),
    id = id,
    title = getString(TITLE_FIELD)
)