package dev.donmanuel.cleanapp.data.api

import dev.donmanuel.cleanapp.data.model.PostDto
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

}