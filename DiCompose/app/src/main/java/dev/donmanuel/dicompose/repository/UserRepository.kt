package dev.donmanuel.dicompose.repository

import dev.donmanuel.dicompose.api.UserApi
import dev.donmanuel.dicompose.model.UserResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) {
    suspend fun getUsers(): UserResponse {
        return api.getUsers()
    }
}