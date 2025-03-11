package dev.donmanuel.retrofitsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dev.donmanuel.retrofitsample.data.RetrofitServiceFactory
import dev.donmanuel.retrofitsample.ui.theme.RetrofitSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val movies = service.listPopularMovies(
                "movie",
                "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MmMzNDQwZGI2YjkyNzUwOGYxMjEzOWU1NWY5MGEyNiIsIm5iZiI6MTY0NjI1NDEzOC42ODUsInN1YiI6IjYyMWZkODNhNmI1ZmMyMDA2YjllNDBiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KkpPJLc0eztQCcSXydhQAOdWV2OWk7AwLoSXsVXZuyE",
                "en-US"
            )
            println("Movies: $movies")
        }


        enableEdgeToEdge()
        setContent {
            RetrofitSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Card {
                        Text(
                            text = "Hello World",
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

