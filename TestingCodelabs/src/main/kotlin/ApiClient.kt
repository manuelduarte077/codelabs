import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable


class ApiClient(private val baseUrl: String) {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getUser(userId: Int): User {
        val response: HttpResponse = client.get("$baseUrl/users/$userId")
        return response.body()
    }
}

@Serializable
data class User(val id: Int, val name: String, val email: String)

suspend fun main() {
    val apiClient = ApiClient("https://jsonplaceholder.typicode.com")
    val user = apiClient.getUser(1)
    println(user)
}

