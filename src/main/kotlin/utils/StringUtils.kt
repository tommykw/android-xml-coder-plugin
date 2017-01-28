package utils

class StringUtils {
    companion object {
        fun takeWithoutSemicolon(str: String): String {
            str.takeLast(1).let {
                if (it == ";") return str.substring(0, str.length - 1)
            }
            return str
        }
    }
}