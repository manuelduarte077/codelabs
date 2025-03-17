package dev.donmanuel.themestoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.donmanuel.themestoreapp.ui.theme.ThemeStoreAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemeStoreAppTheme {
//                val viewModel = hiltViewModel<SettingsViewModel>()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
