package dev.donmanuel.cartoonapp.data.remote

import dev.donmanuel.cartoonapp.domain.model.Cartoon
import retrofit2.http.GET

/**
 * CartoonApi is an interface that defines the endpoints for fetching cartoon data.
 * It uses Retrofit to make network requests.
 */

interface CartoonApi {
    @GET("cartoons2D")
    suspend fun getCartoons2D(): List<Cartoon>

    @GET("cartoons3D")
    suspend fun getCartoons3D(): List<Cartoon>
}