package second.encode

import lombok.SneakyThrows
import second.util.EncoderUtils
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher

class AesCbcEncoder @SneakyThrows constructor(){
    private val secretKeyWord = "YELLOW SUBMARINE"
    private val cipher: Cipher

    init {
        val secretKey = EncoderUtils.getSecretKey(secretKeyWord)
        val iv = EncoderUtils.zeroIv
        cipher = Cipher.getInstance(AES_CBC)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
    }

    @SneakyThrows
    fun encode(message: String): ByteArray? {
        val encodeMessage = message + " ".repeat(16 - message.length % 16)
        return cipher.doFinal(encodeMessage.toByteArray(StandardCharsets.UTF_8))
    }

    companion object {
        private const val AES_CBC = "AES/CBC/NoPadding"
    }
}