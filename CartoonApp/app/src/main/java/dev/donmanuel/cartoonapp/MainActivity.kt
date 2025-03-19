package dev.donmanuel.cartoonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.donmanuel.cartoonapp.presentation.screens.CartoonListScreen
import dev.donmanuel.cartoonapp.ui.theme.CartoonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CartoonAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CartoonListScreen(
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}
