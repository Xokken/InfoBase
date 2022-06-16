package first.encode

import first.encode.util.EncoderUtils
import lombok.SneakyThrows
import javax.crypto.Cipher

class EncoderAesEcbPadding @SneakyThrows constructor(){
    private val cipher: Cipher

    init {
        val secretKey = EncoderUtils.generateSecretKey()
        cipher = Cipher.getInstance(AES_128_ECB)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
    }

    @SneakyThrows
    fun encode(source: ByteArray?): ByteArray? {
        return cipher.doFinal(source)
    }

    companion object {
        private const val AES_128_ECB = "AES/ECB/PKCS5Padding"
    }
}