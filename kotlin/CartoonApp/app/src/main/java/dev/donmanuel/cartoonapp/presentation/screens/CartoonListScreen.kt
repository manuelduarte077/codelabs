package dev.donmanuel.cartoonapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.donmanuel.cartoonapp.domain.model.Cartoon
import dev.donmanuel.cartoonapp.presentation.composables.CartoonDetail
import dev.donmanuel.cartoonapp.presentation.composables.CartoonItem
import dev.donmanuel.cartoonapp.presentation.viewmodel.CartoonViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartoonListScreen(
    viewModel: CartoonViewModel = hiltViewModel(),
    modifier: Modifier
) {

    val cartoons = viewModel.cartoons2D.collectAsState().value
    val favorites = viewModel.favorites.collectAsState().value


    var selectedCartoon by remember { mutableStateOf<Cartoon?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Cartoons")
                }
            )
        }

    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
        ) {
            items(cartoons) { cartoon ->
                CartoonItem(
                    cartoon = cartoon,
                    isFavorite = favorites.any { it.id == cartoon.id },
                    onFavoriteClick = {
                        viewModel.toggleFavorite(cartoon)
                    },
                    onClick = {
                        selectedCartoon = cartoon
                        scope.launch {
                            sheetState.show()
                        }
                    }
                )
            }
        }

        if (selectedCartoon != null) {
            ModalBottomSheet(
                onDismissRequest = { selectedCartoon = null },
                sheetState = sheetState,
            ) {
                CartoonDetail(
                    cartoon = selectedCartoon!!,
                )
            }
        }

    }
}