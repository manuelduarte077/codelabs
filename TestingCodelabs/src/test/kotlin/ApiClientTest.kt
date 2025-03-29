import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiClientTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: ApiClient

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @BeforeAll
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiClient =
            ApiClient(mockWebServer.url("https://jsonplaceholder.typicode.com").toString()) // Base URL de MockWebServer
    }

    @AfterAll
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `obtener usuario devuelve datos correctos`() = runTest {
        val mockUser = User(1, "John Doe", "john@example.com")
        val jsonResponse = moshi.adapter(User::class.java).toJson(mockUser)

        mockWebServer.enqueue(MockResponse().setBody(jsonResponse).setResponseCode(200))

        val user = apiClient.getUser(1)

        assertEquals(1, user.id)
        assertEquals("John Doe", user.name)
        assertEquals("john@example.com", user.email)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `error 404 lanza excepcion`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))


        val exception = assertThrows<Exception> {
            runTest { apiClient.getUser(4) }
        }

        assertEquals("Unexpected response", exception.message)
    }
}