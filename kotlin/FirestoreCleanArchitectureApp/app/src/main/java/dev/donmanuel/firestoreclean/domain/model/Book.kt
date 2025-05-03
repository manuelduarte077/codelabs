package dev.donmanuel.firestoreclean.domain.model

import com.google.firebase.firestore.Exclude

/**
 * Represents a book with an author, unique ID, and title.
 *
 * This data class is designed to be used with Firebase Realtime Database.
 *
 * @property author The author of the book. Can be null if the author is unknown.
 * @property id The unique identifier for the book. This is annotated with `@Exclude`
 *              to prevent it from being serialized/deserialized by Firebase.
 *              It's intended to be managed separately (e.g., as a key in the database).
 *              It's a var because it can be set when retrieved from Firebase.
 * @property title The title of the book. Can be null if the title is unknown.
 */
data class Book(
    val author: String? = null,
    @get:Exclude
    var id: String,
    val title: String? = null
)