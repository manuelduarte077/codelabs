package com.example.characters.repository

import com.example.characters.api.CharacterAPI
import com.example.characters.model.ListCharacter
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApi: CharacterAPI
) {
    suspend fun getAllCharacters(): ListCharacter {
        return characterApi.getAllCharacters()
    }
}