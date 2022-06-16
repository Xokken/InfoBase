package first

import first.decode.SaltOracle
import first.encode.EncoderAesEcbPadding
import first.encode.EncoderWithSalt
import first.validator.InputStringValidator
import java.util.*

private val scanner = Scanner(System.`in`)

fun main() {
    val ecbPadding = EncoderAesEcbPadding()
    val saltEncoder = EncoderWithSalt(ecbPadding)
    val oracle = SaltOracle(saltEncoder)
    println("Выберите режим " +
            "\n1. Ввод соли вручную " +
            "\n2. Генерация "
    )
    val command = scanner.nextInt()
    scanner.nextLine()
    when (command) {
        1 -> withInputSalt(saltEncoder)
        2 -> withGenerateSalt(saltEncoder)
        3 -> println("Что-то не то")
    }
    println(saltEncoder.salt)
    println(oracle.guessSalt(16))
}

fun withGenerateSalt(encoderWithSalt: EncoderWithSalt) {
    encoderWithSalt.generateSalt()
}

fun withInputSalt(encoderWithSalt: EncoderWithSalt) {
    println("Введите необходимую соль")
    val salt = scanner.nextLine().trim { it <= ' ' }
    val isValid = InputStringValidator().validate(salt)
    if (isValid) {
        encoderWithSalt.salt = salt
    } else {
        println("Недопустимые символы, введите символы из диапазона : A-Z")
    }
}