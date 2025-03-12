package dev.donmanuel.movieappkt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.donmanuel.movieappkt.model.MovieModel
import dev.donmanuel.movieappkt.ui.theme.MovieAppKTTheme
import dev.donmanuel.movieappkt.utils.Constants.IMAGE_BASE_URL
import dev.donmanuel.movieappkt.viewmodel.MovieViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppKTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel = MovieViewModel()
                    MovieScreen(innerPadding, viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    paddingValues: PaddingValues,
    viewModel: MovieViewModel
) {

    val movieList by viewModel.movieList.collectAsState()

    Log.d("movieList", movieList.toString())

    var openModalBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    var bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    var selectedMovie by rememberSaveable { mutableStateOf<MovieModel?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            columns = GridCells.Fixed(2)
        ) {

            items(movieList) { movie ->
                val imageUrl = "$IMAGE_BASE_URL${movie.poster}"

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    onClick = {
                        selectedMovie = movie
                        openModalBottomSheet = !openModalBottomSheet
                    }
                ) {
                    MovieItem(
                        movie = movie,
                        imageUrl = imageUrl
                    )

                    if (openModalBottomSheet && selectedMovie != null) {
                        ModalBottomSheet(
                            onDismissRequest = { openModalBottomSheet = false },
                            sheetState = bottomSheetState,
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    text = "${selectedMovie?.title}",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.headlineSmall,
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    text = "Estreno: ${selectedMovie?.releaseDate}",
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    text = "Resumen: ${selectedMovie?.description}",
                                    textAlign = TextAlign.Justify,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun MovieItem(
    movie: MovieModel,
    imageUrl: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(2 / 3f)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = movie.description,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Black.copy(alpha = 0.7f), shape = CircleShape)
                    .align(Alignment.TopEnd)
            ) {
                CircularProgressIndicator(
                    progress = { movie.voteCount.toFloat() / 10 },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(48.dp),
                    color = Color.Green,
                    trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
                )

                Text(
                    text = "%.1f".format(movie.voteCount.toFloat()),
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,

                    )
            }
        }

        // Movie title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.7f))
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = movie.title,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}









