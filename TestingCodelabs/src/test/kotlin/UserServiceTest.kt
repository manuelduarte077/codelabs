import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserServiceTest {

    private val userRepository: UserRepository = mock()
    private val userService = UserService(userRepository)

    @Test
    fun `test obtener nombre de usuario`() {
        whenever(userRepository.getUser(1)).thenReturn("Alice")

        val result = userService.getUserName(1)

        assertEquals("Pepe", result)
    }
}