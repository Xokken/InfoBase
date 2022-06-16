package second

import second.decode.AesCbcDecoder
import second.encode.AesCbcEncoder
import second.mac.MessageChecker
import second.virus.CodeChanger

val encoder = AesCbcEncoder()
val decoder = AesCbcDecoder()
val codeChanger = CodeChanger()
val messageChecker = MessageChecker()
const val sourceMessage = "Message (\"Anime girl!\");"

fun main() {
    val encodedSourceMessage = encoder.encode(sourceMessage)
    messageChecker.setMark(encodedSourceMessage)
    val hackedMessage = encodedSourceMessage?.let { codeChanger.changeCode(it) }
    println(messageChecker.checkMark(hackedMessage))
    val newMessage = decoder.decode(hackedMessage)
    println(String(newMessage!!))
}
