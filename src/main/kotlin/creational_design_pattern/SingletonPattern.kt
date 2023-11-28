package creational_design_pattern

import java.util.regex.Pattern

/**
 * Singleton is a creational design pattern that lets you ensure
 * that a class has only one instance, while providing a global access
 * point to this instance.
 *
 * @author arfan
 * */

object ValidateName {
    fun getNameWithRegex(email: String) = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+").toRegex().matches(email)
}

fun main() {
    val emailStatus = if (ValidateName.getNameWithRegex("example@gmail.com")) "Valid" else "Invalid"

    print(emailStatus)
}