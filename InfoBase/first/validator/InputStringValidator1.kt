package first.validator

class InputStringValidator {
    fun validate(inputString: String): Boolean {
        for (character in inputString.toCharArray()) {
            if (character < 'A' || character > 'Z') {
                return false
            }
        }
        return true
    }
}