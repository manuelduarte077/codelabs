package dev.donmanuel.restexample.presentation.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.restexample.Graph
import dev.donmanuel.restexample.data.network.models.MealX
import dev.donmanuel.restexample.data.repository.MealRepository
import dev.donmanuel.restexample.utils.Response

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MealRepository = Graph.repository
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    fun fetchMealById(id: String) {
        viewModelScope.launch {
            repository.fetchMealById(id).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _detailState.update {
                            it.copy(
                                isLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                        _detailState.update {
                            it.copy(
                                isLoading = false, error = null,
                                meals = result.data.meals[0]
                            )
                        }
                    }

                    is Response.Error -> {
                        _detailState.update {
                            it.copy(
                                isLoading = false, error = result.error?.message
                            )
                        }
                    }
                }
            }
        }

    }
}

data class DetailState(
    val meals: MealX? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)