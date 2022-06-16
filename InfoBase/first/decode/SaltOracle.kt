package first.decode

import first.encode.EncoderWithSalt
import java.nio.charset.StandardCharsets

class SaltOracle(private val encoderWithSalt: EncoderWithSalt) {
    fun guessSalt(blockSize: Int): String {
        var i = 1
        val salt = StringBuilder()
        var currentChar = getNextChar("", blockSize)
        while (currentChar != '!') {
            i++
            salt.append(currentChar)
            currentChar = getNextChar(salt.toString(), blockSize)
        }
        return salt.toString()
    }

    private fun getNextChar(currentSalt: String, blockSize: Int): Char {
        val saltLength = currentSalt.toByteArray(StandardCharsets.UTF_8).size
        val repeatForFullBlocks = blockSize - saltLength % blockSize - 1
        val newMessage = "A".repeat(repeatForFullBlocks)
        val rightMessage = encoderWithSalt.encode(newMessage)
        val ackBlock = "A".repeat(repeatForFullBlocks) + currentSalt
        for (i in 0..26) {
            val currentChar = ('A'.code + i).toChar()
            val currentMessage = ackBlock + currentChar
            val currentBytes = encoderWithSalt.encode(currentMessage)
            val fullBlocks = saltLength / blockSize
            if (isEqualsParts(rightMessage, currentBytes, fullBlocks * blockSize, blockSize)) {
                return currentChar
            }
        }
        return '!'
    }

    private fun isEqualsParts(first: ByteArray?, second: ByteArray?, start: Int, length: Int): Boolean {
        for (i in start until start + length) {
            if (first!![i] != second!![i]) {
                return false
            }
        }
        return true
    }
}