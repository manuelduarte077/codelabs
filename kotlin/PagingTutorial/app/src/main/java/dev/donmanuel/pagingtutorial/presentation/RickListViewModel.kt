package dev.donmanuel.pagingtutorial.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.donmanuel.pagingtutorial.data.RickRepository
import dev.donmanuel.pagingtutorial.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RickListViewModel @Inject constructor(rickRepository: RickRepository) : ViewModel() {

    val characters: Flow<PagingData<CharacterModel>> = rickRepository.getAllCharacters()

}