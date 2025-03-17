package dev.donmanuel.themestoreapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.themestoreapp.domain.usecase.GetDarkModeUseCase
import dev.donmanuel.themestoreapp.domain.usecase.SaveDarkModeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing settings-related data and actions.
 *
 * @param saveDarkModeUseCase Use case for saving the dark mode preference.
 * @param getDarkModeUseCase Use case for retrieving the dark mode preference.
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveDarkModeUseCase: SaveDarkModeUseCase,
    getDarkModeUseCase: GetDarkModeUseCase,
) : ViewModel() {
    val darkModeState: Flow<Boolean> = getDarkModeUseCase()

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            saveDarkModeUseCase(enabled)
        }
    }
}