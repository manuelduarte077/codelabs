import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MathUtilsTest {

    private val mathUtils = MathUtils()

    @Test
    fun `division entre cero lanza excepcion`() {
        val exception = assertThrows<IllegalArgumentException> {
            mathUtils.divide(10, 0)
        }
        assertEquals("No se puede dividir por cero", exception.message)
    }
}