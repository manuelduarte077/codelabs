class MathUtils {
    fun divide(a: Int, b: Int): Int {
        require(b != 0) { "No se puede dividir por cero" }
        return a / b
    }
}

fun main() {
    val mathUtils = MathUtils()
    println(mathUtils.divide(10, 2))
}