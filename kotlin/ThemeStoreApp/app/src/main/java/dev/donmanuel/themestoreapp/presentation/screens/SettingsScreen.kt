package dev.donmanuel.themestoreapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.donmanuel.themestoreapp.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    viewModel: SettingsViewModel
) {
    val darkMode by viewModel.darkModeState.collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Dark Mode: $darkMode",
            style = MaterialTheme.typography.titleLarge
        )

        Switch(
            checked = darkMode,
            onCheckedChange = { viewModel.toggleDarkMode(it) },
        )
    }

}