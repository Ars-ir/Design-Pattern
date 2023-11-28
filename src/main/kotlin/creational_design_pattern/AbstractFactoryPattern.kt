package creational_design_pattern

import java.util.regex.Pattern

/**
 * Abstract Factory is a creational design pattern that lets you produce
 * families of related objects without specifying their concrete classes.
 *
 * @author arfan
 * */

private val EMAIL_PATTERN = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
).toRegex()

interface Validator {
    val email: String
    fun validate(): Boolean
}

class FirstEmail(override val email: String) : Validator {
    override fun validate(): Boolean = EMAIL_PATTERN.matches(email)
}

class SecondEmail(override val email: String): Validator {
    override fun validate(): Boolean = EMAIL_PATTERN.matches(email)
}

class GetFirstEmail: EmailValidator() {
    override fun getValues(): Validator = FirstEmail("example@Gmail.com")
}

class GetSecondEmail: EmailValidator() {
    override fun getValues(): Validator = SecondEmail("example@gmail.com")
}

abstract class EmailValidator {
    abstract fun getValues(): Validator

    companion object {
        inline fun <reified T> validator(): EmailValidator {
            return when (T::class) {
                GetFirstEmail::class -> GetFirstEmail()
                GetSecondEmail::class -> GetSecondEmail()
                else -> throw IllegalStateException("Unidentified")
            }
        }
    }
}

fun main() {
    val firstEmail = EmailValidator.validator<GetFirstEmail>()
    val secondEmail = EmailValidator.validator<GetSecondEmail>()

    println(firstEmail.getValues().validate())
    println(secondEmail.getValues().validate())
}