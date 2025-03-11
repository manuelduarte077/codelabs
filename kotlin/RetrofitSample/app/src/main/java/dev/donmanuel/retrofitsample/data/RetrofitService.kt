package dev.donmanuel.retrofitsample.data

import dev.donmanuel.retrofitsample.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query

/**
 * Interface defining the Retrofit service for interacting with the movie/TV show API.
 *
 * This interface provides methods to fetch data related to movies and TV shows,
 * such as popular titles, using the provided API endpoints.
 */
interface RetrofitService {

    @GET("discover/{type}?sort_by=popularity.desc")
    suspend fun listPopularMovies(
        @Part("type") type: String, // movie or tv
        @Query("api_key") apiKey: String, // your api key
        @Query("region") region: String = "US",
    ): RemoteResult
}

/**
 * Factory object to create an instance of the RetrofitService.
 *
 * This object provides a method to create a Retrofit service instance
 * with the base URL and other configurations.
 */

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}