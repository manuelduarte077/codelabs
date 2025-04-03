package dev.donmanuel.bmicalculator

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.donmanuel.bmicalculator.presentation.CalculatorScreen
import dev.donmanuel.bmicalculator.presentation.CalculatorViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = getViewModel(
            key = "calculator-screen",
            factory = viewModelFactory {
                CalculatorViewModel()
            }
        )

        CalculatorScreen(viewModel)
    }
}