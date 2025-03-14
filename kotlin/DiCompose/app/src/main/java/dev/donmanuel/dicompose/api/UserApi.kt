package dev.donmanuel.dicompose.api

import dev.donmanuel.dicompose.model.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): UserResponse
}