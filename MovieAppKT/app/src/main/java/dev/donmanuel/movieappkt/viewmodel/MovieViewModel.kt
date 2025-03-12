package dev.donmanuel.movieappkt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.movieappkt.model.MovieModel
import dev.donmanuel.movieappkt.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    private var _movieList = MutableStateFlow<List<MovieModel>>(emptyList())
    val movieList = _movieList.asStateFlow()

    init {
        getMovieList()
    }

    fun getMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.retrofit.nowPlaying()

            withContext(Dispatchers.Main) {
                _movieList.value = response.body()?.result ?: emptyList()
            }
        }
    }
}