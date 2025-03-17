package dev.donmanuel.firestoreclean.presentation.book_list.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.donmanuel.firestoreclean.R

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(
                    id = R.string.app_name
                )
            )
        },
        backgroundColor = androidx.compose.material.MaterialTheme.colors.primary,
        contentColor = androidx.compose.material.MaterialTheme.colors.onPrimary,
        elevation = 4.dp
    )
}