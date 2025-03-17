package dev.donmanuel.themestoreapp.domain.usecase

import dev.donmanuel.themestoreapp.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the dark mode preference.
 *
 * @param repository The repository used to retrieve the dark mode preference.
 */
class GetDarkModeUseCase @Inject constructor(
    private val repository: DataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.darkModeFlow
}
