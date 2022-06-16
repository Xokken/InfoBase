package second.util

import lombok.SneakyThrows
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncoderUtils {
    @SneakyThrows
    fun getSecretKey(keyWord: String): SecretKey {
        return SecretKeySpec(keyWord.toByteArray(StandardCharsets.UTF_8), EncryptConstants.AES)
    }

    val zeroIv: IvParameterSpec
        get() {
            val bytes = ByteArray(16)
            return IvParameterSpec(bytes)
        }
}