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

            Log.d("JokeClient", "Request URL: https://icanhazdadjoke.com/")
            Log.d("JokeClient", "Response status: ${response.status.value}")

            val responseBody = response.body<String>()
            Log.d("JokeClient", "Raw response body: $responseBody")

            // Now try to parse it into a Joke object
            return Json.decodeFromString<Joke>(responseBody)

        } catch (e: Exception) {
            return null
        }
    }
}