package dev.donmanuel.movieappkt.network.response

import com.google.gson.annotations.SerializedName
import dev.donmanuel.movieappkt.model.MovieModel

data class MovieResponse(

    @SerializedName("results")
    var result: List<MovieModel>

)