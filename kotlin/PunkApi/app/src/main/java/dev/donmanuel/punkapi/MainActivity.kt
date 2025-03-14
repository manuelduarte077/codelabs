package dev.donmanuel.punkapi

import android.os.Bundle
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
import dev.donmanuel.punkapi.api.PunkApi
import dev.donmanuel.punkapi.model.PunkModel
import dev.donmanuel.punkapi.ui.theme.PunkApiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PunkApiTheme {
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
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.beers.value?.name?.let { beersName ->
            Text(
                text = beersName,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
        }

        /// Mostrar todo el detalle de la cerveza
        viewModel.beers.value?.description?.let { beersDescription ->
            Text(
                text = beersDescription,
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
        }

        /// Mostrar la imagen de la cerveza
        viewModel.beers.value?.image?.let { beersImage ->
            Text(
                text = "https://punkapi.online/v3/images/$beersImage",
                modifier = Modifier
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
        }



        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                viewModel.fetchBeer()
            }) {
            Text(text = "Show me a beer")
        }
    }
}


class MainViewModel : ViewModel() {
    private val punkClient = PunkApi()
    val beers = mutableStateOf<PunkModel?>(null)

    fun fetchBeer() {
        viewModelScope.launch {
            beers.value = punkClient.getPunk()
        }
    }
}








