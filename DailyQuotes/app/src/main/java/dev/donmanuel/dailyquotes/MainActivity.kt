package dev.donmanuel.dailyquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.donmanuel.dailyquotes.ui.screens.QuoteScreen
import dev.donmanuel.dailyquotes.ui.theme.DailyQuotesTheme
import dev.donmanuel.dailyquotes.viewmodels.QuoteViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyQuotesTheme {
                val viewModel: QuoteViewModel = koinViewModel()
                QuoteScreen(
                    viewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyQuotesTheme {
        val viewModel: QuoteViewModel = koinViewModel()
        QuoteScreen(
            viewModel
        )
    }
}
