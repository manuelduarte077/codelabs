package dev.donmanuel.firestoreclean.core

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Global constants used across the application.
 */
const val TAG = "AppTag"

/**
 * Names for database fields corresponding to book properties.
 */
const val AUTHOR_FIELD = "author"
const val ID_FIELD = "id"
const val TITLE_FIELD = "title"

/**
 * Default messages for cases when a book's author or title is not specified.
 */
const val NO_BOOK_AUTHOR = "No book author"
const val NO_BOOK_TITLE = "No book title"

/**
 * Logs an error message using Android's Log utility.
 *
 * @param errorMessage The error message to log.
 */
fun logErrorMessage(errorMessage: String) = Log.e(TAG, errorMessage)

/**
 * Displays a Toast message with a long duration.
 *
 * @param context The Context used to display the Toast.
 * @param message The message to be shown.
 */
fun showToastMessage(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
