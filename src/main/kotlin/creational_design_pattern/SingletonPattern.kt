package creational_design_pattern

import java.util.regex.Pattern

object ValidateName {
    fun getNameWithRegex(fullName: String) = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+").toRegex().matches(fullName)
}

fun main() {
    val emailStatus = if (ValidateName.getNameWithRegex("example@gmail.com")) "Valid" else "Invalid"

    print(emailStatus)
}