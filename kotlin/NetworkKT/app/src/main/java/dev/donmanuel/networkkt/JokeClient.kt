package dev.donmanuel.networkkt

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class JokeClient {
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

    suspend fun getJoke(): Joke? {
        try {
            val response = httpClient.get("https://icanhazdadjoke.com/") {
                headers {
                    append("Accept", "application/json")
                }
            }

            // Now try to parse it into a Joke object
            return response.body<Joke?>()

        } catch (e: Exception) {
            return null
        }
    }
}