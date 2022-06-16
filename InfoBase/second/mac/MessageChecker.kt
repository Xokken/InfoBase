package second.mac

class MessageChecker {
    private val mark = ByteArray(16)
    fun setMark(message: ByteArray?) {
        System.arraycopy(message, message!!.size - 16, mark, 0, 16)
    }

    fun checkMark(message: ByteArray?): Boolean {
        val timeBuffer = ByteArray(16)
        System.arraycopy(message, message!!.size - 16, timeBuffer, 0, 16)
        for (i in 0..15) {
            if (mark[i] != timeBuffer[i]) {
                return false
            }
        }
        return true
    }
}