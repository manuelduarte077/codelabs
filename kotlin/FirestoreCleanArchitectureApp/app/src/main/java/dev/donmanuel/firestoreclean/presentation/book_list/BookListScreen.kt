package dev.donmanuel.firestoreclean.presentation.book_list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.donmanuel.firestoreclean.R
import dev.donmanuel.firestoreclean.components.LoadingIndicator
import dev.donmanuel.firestoreclean.core.logErrorMessage
import dev.donmanuel.firestoreclean.core.showToastMessage
import dev.donmanuel.firestoreclean.domain.model.Response
import dev.donmanuel.firestoreclean.presentation.book_list.composables.AddBookAlertDialog
import dev.donmanuel.firestoreclean.presentation.book_list.composables.AddBookFloatingActionButton
import dev.donmanuel.firestoreclean.presentation.book_list.composables.BookListContent
import dev.donmanuel.firestoreclean.presentation.book_list.composables.EmptyBookListContent
import dev.donmanuel.firestoreclean.presentation.book_list.composables.TopBar

@Composable
fun BookListScreen(
    viewModel: BookListViewModel = viewModel()
) {
    val context = LocalContext.current
    var openAddBookDialog by remember { mutableStateOf(false) }
    val bookListResponse by viewModel.bookListState.collectAsStateWithLifecycle()
    val addBookResponse by viewModel.addBookState.collectAsStateWithLifecycle()
    val updateBookResponse by viewModel.updateBookState.collectAsStateWithLifecycle()
    val deleteBookResponse by viewModel.deleteBookState.collectAsStateWithLifecycle()
    val invalidBookFieldMessage = stringResource(R.string.invalid_book_field_message)
    val bookActionMessage = stringResource(R.string.book_action_message)

    Scaffold(
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            AddBookFloatingActionButton(
                onAddBookFloatingActionButtonClick = {
                    openAddBookDialog = true
                }
            )
        }
    ) { innerPadding ->
        when (val bookListResponse = bookListResponse) {
            is Response.Idle -> {}
            is Response.Loading -> LoadingIndicator()
            is Response.Success -> bookListResponse.data?.let { bookList ->
                if (bookList.isEmpty()) {
                    EmptyBookListContent(
                        innerPadding = innerPadding
                    )
                } else {
                    BookListContent(
                        innerPadding = innerPadding,
                        bookList = bookList,
                        onUpdateBook = viewModel::updateBook,
                        onInvalidBookField = { bookField ->
                            showToastMessage(context, "$bookField $invalidBookFieldMessage")
                        },
                        onDeleteBook = viewModel::deleteBook
                    )
                }
            }

            is Response.Failure -> bookListResponse.e?.message?.let { errorMessage ->
                LaunchedEffect(errorMessage) {
                    logErrorMessage(errorMessage)
                    showToastMessage(context, errorMessage)
                }
            }
        }
    }

    if (openAddBookDialog) {
        AddBookAlertDialog(
            onAddBook = viewModel::addBook,
            onInvalidBookField = { bookField ->
                showToastMessage(context, "$bookField $invalidBookFieldMessage")
            },
            onAddBookDialogCancel = {
                openAddBookDialog = false
            }
        )
    }

    when (val addBookResponse = addBookResponse) {
        is Response.Idle -> {}
        is Response.Loading -> LoadingIndicator()
        is Response.Success -> LaunchedEffect(Unit) {
            showToastMessage(context, "${BookAction.ADD} $bookActionMessage")
            viewModel.resetAddBookState()
        }

        is Response.Failure -> addBookResponse.e?.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                logErrorMessage(errorMessage)
                showToastMessage(context, errorMessage)
                viewModel.resetAddBookState()
            }
        }
    }

    when (val updateBookResponse = updateBookResponse) {
        is Response.Idle -> {}
        is Response.Loading -> LoadingIndicator()
        is Response.Success -> LaunchedEffect(Unit) {
            showToastMessage(context, "${BookAction.UPDATE} $bookActionMessage")
            viewModel.resetUpdateBookState()
        }

        is Response.Failure -> updateBookResponse.e?.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                logErrorMessage(errorMessage)
                showToastMessage(context, errorMessage)
                viewModel.resetUpdateBookState()
            }
        }
    }

    when (val deleteBookResponse = deleteBookResponse) {
        is Response.Idle -> {}
        is Response.Loading -> LoadingIndicator()
        is Response.Success -> LaunchedEffect(Unit) {
            showToastMessage(context, "${BookAction.DELETE} $bookActionMessage")
            viewModel.resetDeleteBookState()
        }

        is Response.Failure -> deleteBookResponse.e?.message?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                logErrorMessage(errorMessage)
                showToastMessage(context, errorMessage)
                viewModel.resetDeleteBookState()
            }
        }
    }
}

enum class BookAction() {
    ADD,
    UPDATE,
    DELETE
}

enum class BookField() {
    TITLE,
    AUTHOR
}