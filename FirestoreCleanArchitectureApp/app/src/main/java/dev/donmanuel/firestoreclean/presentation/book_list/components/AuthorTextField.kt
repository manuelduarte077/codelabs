package dev.donmanuel.firestoreclean.presentation.book_list.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import dev.donmanuel.firestoreclean.R

@Composable
fun AuthorTextField(
    author: TextFieldValue,
    onAuthorChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = author,
        onValueChange = onAuthorChange,
        placeholder = {
            Text(
                text = stringResource(
                    id = R.string.book_author
                )
            )
        },
        singleLine = true
    )
}