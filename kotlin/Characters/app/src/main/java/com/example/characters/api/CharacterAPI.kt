package com.example.characters.api

import com.example.characters.model.ListCharacter
import retrofit2.http.GET

interface CharacterAPI {
    @GET("character")
    suspend fun getAllCharacters(): ListCharacter
}