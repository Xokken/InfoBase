package second.decode

import lombok.SneakyThrows
import second.util.EncoderUtils
import javax.crypto.Cipher

class AesCbcDecoder @SneakyThrows constructor() {
    private val secretKeyWord = "YELLOW SUBMARINE"
    private val cipher: Cipher

    init {
        val secretKey = EncoderUtils.getSecretKey(secretKeyWord)
        val iv = EncoderUtils.zeroIv
        cipher = Cipher.getInstance(AES_CBC)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
    }

    @SneakyThrows
    fun decode(encoded: ByteArray?): ByteArray? {
        return cipher.doFinal(encoded)
    }

    companion object {
        private const val AES_CBC = "AES/CBC/NoPadding"
    }
}