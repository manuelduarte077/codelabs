package dev.donmanuel.firestoreclean.domain.model

import com.google.firebase.firestore.Exclude

data class Book(
    val author: String? = null,
    @get:Exclude
    var id: String,
    val title: String? = null
)