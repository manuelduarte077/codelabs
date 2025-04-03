package dev.donmanuel.bmicalculator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BMI Calculator",
    ) {
        App()
    }
}