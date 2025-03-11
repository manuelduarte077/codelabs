package dev.donmanuel.networkkt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.networkkt.ui.theme.NetworkKTTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkKTTheme {
                val viewModel by viewModels<MainViewModel>()
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        viewModel.joke.value?.joke?.let { jokeText ->
            Text(
                text = jokeText,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
        } ?: run {
            Text(text = "Press the button to get a joke")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                viewModel.fetchJoke()
            }
        ) {
            Text(text = "Get a new joke")
        }
    }
}

class MainViewModel : ViewModel() {
    private val jokeClient = JokeClient()
    val joke = mutableStateOf<Joke?>(null)

    fun fetchJoke() {
        viewModelScope.launch {
            joke.value = jokeClient.getJoke()

            Log.d("MainViewModel", "dev.donmanuel.networkkt.Joke fetched: ${joke.value?.joke}")
        }
    }
}
