package dev.donmanuel.themestoreapp.domain.usecase

import dev.donmanuel.themestoreapp.data.repository.DataStoreRepository
import javax.inject.Inject

/**
 * Use case for saving the dark mode preference.
 *
 * @param repository The repository used to save the dark mode preference.
 */
class SaveDarkModeUseCase @Inject constructor(private val repository: DataStoreRepository) {
    suspend operator fun invoke(enabled: Boolean) = repository.saveDarkMode(enabled)
}