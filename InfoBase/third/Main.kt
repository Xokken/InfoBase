package third

import java.util.concurrent.ThreadLocalRandom
import kotlin.math.pow

fun main() {
    var start = System.currentTimeMillis()
    powByRemainder(192, Int.MAX_VALUE, 193)
    var end = System.currentTimeMillis()
    println(end - start)
    start = System.currentTimeMillis()
    123.0.pow(Int.MAX_VALUE.toDouble())
    end = System.currentTimeMillis()
    println(end - start)
}

fun powByRemainder(base: Int, exponent: Int, remainder: Int): Int {
    var result: Long = 1
    var i = 0
    while (i < exponent) {
        if (i == 0 || i * 2 > exponent || i * 2 < 0) {
            result *= (base % remainder).toLong()
            i++
        } else {
            result *= result
            i *= 2
        }
        result %= remainder.toLong()
    }
    return result.toInt()
}

class KeyGen(private val base: Int, private val remainder: Int) {
    private val secretKey = ThreadLocalRandom.current().nextInt(0, 10000)
    fun generateHelloKey(): Int {
        return powByRemainder(base, secretKey, remainder)
    }

    fun generateGeneralKey(neighbourKey: Int): Int {
        return powByRemainder(neighbourKey, secretKey, remainder)
    }
}
