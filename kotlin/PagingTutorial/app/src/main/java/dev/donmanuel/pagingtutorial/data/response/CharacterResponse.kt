package dev.donmanuel.pagingtutorial.data.response

import com.google.gson.annotations.SerializedName
import dev.donmanuel.pagingtutorial.presentation.model.CharacterModel

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String,
) {
    fun toPresentation(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            image = image,
            isAlive = status == "Alive"
        )
    }
}