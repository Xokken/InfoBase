package first.encode

import first.encode.util.EncoderUtils
import java.nio.charset.StandardCharsets

class EncoderWithSalt(private val encoder: EncoderAesEcbPadding) {
    var salt: String? = null

    fun generateSalt() {
        salt = EncoderUtils.generateSalt()
    }

    fun encode(source: String): ByteArray? {
        val message = source + salt
        return encoder.encode(message.toByteArray(StandardCharsets.UTF_8))
    }
}