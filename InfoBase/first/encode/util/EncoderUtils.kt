package first.encode.util

import java.security.NoSuchAlgorithmException
import java.util.concurrent.ThreadLocalRandom
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object EncoderUtils {
    fun generateSecretKey(): SecretKey {
        val generator: KeyGenerator
        generator = try {
            KeyGenerator.getInstance(EncryptConstants.AES)
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
        generator.init(EncryptConstants.KEY_SIZE)
        return generator.generateKey()
    }

    fun generateSalt(): String {
        val builder = StringBuilder()
        val size = ThreadLocalRandom.current().nextInt(17, 32)
        for (i in 0 until size) {
            builder.append(ThreadLocalRandom.current().nextInt('A'.code, 'Z'.code).toChar())
        }
        return builder.toString()
    }
}