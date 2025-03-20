package dev.donmanuel.cartoonapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.donmanuel.cartoonapp.presentation.composables.CartoonItem
import dev.donmanuel.cartoonapp.presentation.viewmodel.CartoonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartoonListScreen(viewModel: CartoonViewModel = hiltViewModel()) {

    val cartoons = viewModel.cartoons.collectAsState().value
    val isLoading = cartoons.isEmpty()
    val favorites = viewModel.favorites.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cartoon List",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            items(cartoons) { cartoon ->
                CartoonItem(cartoon, isFavorite = favorites.any { it.id == cartoon.id }) {
                    viewModel.toggleFavorite(cartoon)
                }
            }
        }
    }
}