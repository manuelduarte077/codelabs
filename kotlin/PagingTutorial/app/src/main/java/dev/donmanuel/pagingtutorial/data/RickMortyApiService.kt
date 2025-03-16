package dev.donmanuel.pagingtutorial.data

import dev.donmanuel.pagingtutorial.data.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApiService {
    @GET("/api/character")
    suspend fun getCharacters(@Query("page") page: Int): ResponseWrapper
}