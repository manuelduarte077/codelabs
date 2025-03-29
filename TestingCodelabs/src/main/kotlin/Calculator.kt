import kotlin.math.pow

class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    fun multiply(a: Int, b: Int): Int {
        return a * b
    }

    fun divide(a: Int, b: Int): Int {
        return a / b
    }

    fun remainder(a: Int, b: Int): Int {
        return a % b
    }

    fun power(a: Int, b: Int): Int {
        return a.toDouble().pow(b.toDouble()).toInt()
    }

    fun square(a: Int): Int {
        return a.toDouble().pow(2.0).toInt()
    }

    fun cube(a: Int): Int {
        return a.toDouble().pow(3.0).toInt()
    }
}

fun main() {
    val calculator = Calculator()
    println(calculator.add(1, 2))
}