package second.virus

import second.encode.AesCbcEncoder

class CodeChanger {
    fun changeCode(source: ByteArray): ByteArray {
        val encoder = AesCbcEncoder()
        val neededMessage = "alert(\"You are pwned!\"); //"
        val lastIndexBlock = source.size - 16
        val lastBlock = ByteArray(16)
        System.arraycopy(source, source.size - 16, lastBlock, 0, 16)
        val hacked = encoder.encode(neededMessage)
        return mergeArrays(hacked, lastBlock)
    }

    fun mergeArrays(firstArray: ByteArray?, secondArray: ByteArray): ByteArray {
        val newArray = ByteArray(firstArray!!.size + secondArray.size)
        System.arraycopy(firstArray, 0, newArray, 0, firstArray.size)
        System.arraycopy(secondArray, 0, newArray, firstArray.size, secondArray.size)
        return newArray
    }
}