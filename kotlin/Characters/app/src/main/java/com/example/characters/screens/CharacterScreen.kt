package com.example.characters.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.characters.model.Character

@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LazyColumn {
        items(state) { character ->
            Log.d("CharacterScreen", "Rendering character: ${character.name}")
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row {
            Surface(
                modifier.size(120.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = "${character.name} image",
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }
}
