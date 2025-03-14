package dev.donmanuel.punkapi.api

import android.util.Log
import dev.donmanuel.punkapi.model.PunkModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class PunkApi {

    private val httpClient = HttpClient(OkHttp) {
        install(Logging)
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    suspend fun getPunk(): PunkModel? {
        try {
            val response = httpClient.get("https://punkapi.online/v3/beers/random") {
                headers {
                    append("Accept", "application/json")
                }
            }

            return response.body<PunkModel>()
        } catch (e: Exception) {
            return null
        }
    }
}