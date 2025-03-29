package dev.donmanuel.restexample.presentation.meals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.donmanuel.restexample.data.network.models.Meal

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    mealViewModel: MealViewModel = viewModel(),
    onMealClick: (Meal) -> Unit
) {
    val mealState by mealViewModel.homeState.collectAsState()

    Scaffold {
        Column(modifier) {
            if (mealState.isLoading) {
                CircularProgressIndicator()
            }

            if (mealState.error != null) {
                Text(
                    mealState.error.orEmpty(),
                    style = MaterialTheme.typography.h6
                )
            }

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(mealState.meals) { meal ->
                    FoodItem(meal = meal) {
                        onMealClick(meal)
                    }
                }
            }
        }
    }
}
