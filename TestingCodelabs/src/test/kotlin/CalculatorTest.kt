import kotlin.test.Test

class CalculatorTest {

    @Test
    fun `addition of two numbers`() {
        val calculator = Calculator()
        val result = calculator.add(1, 2)
        assert(result == 3)
    }

    @Test
    fun `subtraction of two numbers`() {
        val calculator = Calculator()
        val result = calculator.subtract(2, 1)
        assert(result == 1)
    }

    @Test
    fun `multiplication of two numbers`() {
        val calculator = Calculator()
        val result = calculator.multiply(2, 3)
        assert(result == 6)
    }

    @Test
    fun `division of two numbers`() {
        val calculator = Calculator()
        val result = calculator.divide(6, 3)
        assert(result == 2)
    }

    @Test
    fun `remainder of two numbers`() {
        val calculator = Calculator()
        val result = calculator.remainder(7, 3)
        assert(result == 1)
    }

    @Test
    fun `power of two numbers`() {
        val calculator = Calculator()
        val result = calculator.power(2, 3)
        assert(result == 8)
    }

    @Test
    fun `square of a number`() {
        val calculator = Calculator()
        val result = calculator.square(3)
        assert(result == 9)
    }

    @Test
    fun `cube of a number`() {
        val calculator = Calculator()
        val result = calculator.cube(3)
        assert(result == 27)
    }

}