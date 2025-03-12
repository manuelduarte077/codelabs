package dev.donmanuel.movieappkt.network

import dev.donmanuel.movieappkt.network.response.MovieResponse
import dev.donmanuel.movieappkt.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("now_playing?api_key=${Constants.API_KEY}&language=en-US")
    suspend fun nowPlaying(): Response<MovieResponse>

}