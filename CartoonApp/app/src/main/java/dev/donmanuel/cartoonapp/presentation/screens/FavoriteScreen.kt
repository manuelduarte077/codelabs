package dev.donmanuel.cartoonapp.presentation.screens

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.donmanuel.cartoonapp.presentation.composables.FavoriteCartoonItem
import dev.donmanuel.cartoonapp.presentation.viewmodel.CartoonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(modifier: Modifier, viewModel: CartoonViewModel = hiltViewModel()) {

    val favorites = viewModel.favorites.collectAsState().value

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Favoritos") },
            )
        }

    ) { padding ->
        if (favorites.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text("No hay favoritos guardados")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
            ) {
                items(favorites) { cartoon ->
                    FavoriteCartoonItem(cartoon, onRemoveClick = {
                        viewModel.removeFavorite(cartoon)
                    })
                }
            }
        }
    }
}