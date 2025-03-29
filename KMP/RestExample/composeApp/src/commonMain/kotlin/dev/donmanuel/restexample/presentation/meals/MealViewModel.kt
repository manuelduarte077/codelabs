package dev.donmanuel.restexample.presentation.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.restexample.Graph
import dev.donmanuel.restexample.data.network.models.Meal
import dev.donmanuel.restexample.data.repository.MealRepository
import dev.donmanuel.restexample.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealViewModel(
    private val mealRepository: MealRepository = Graph.repository
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            mealRepository.fetchMeals().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _homeState.update {
                            it.copy(
                                isLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false, error = null,
                                meals = result.data.meals
                            )
                        }
                    }

                    is Response.Error -> {
                        _homeState.update {
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

data class HomeState(
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)