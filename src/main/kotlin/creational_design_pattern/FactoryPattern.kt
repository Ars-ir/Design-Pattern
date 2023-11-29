package creational_design_pattern

import org.junit.jupiter.api.Assertions
import java.util.regex.Pattern

/**
 * Factory Method is a creational design pattern that provides
 * an interface for creating objects in a superclass, but allows
 * subclasses to alter the type of objects that will be created.
 *
 * @author arfan
 * */

fun String.emailPattern() = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
).toRegex().matches(this)

fun String.isEmptyPattern() = Pattern.compile(
    "^\\S+[^ ]\$"
).toRegex().matches(this)

sealed class Account(open val validate: Boolean)

class EmailPremium(override val validate: Boolean): Account(validate)

class EmailRegular(override val validate: Boolean): Account(validate)

object LoginFactory {
    inline fun <reified T> requestLogin(email: String, password: String): Account {
        return when(T::class) {
            EmailPremium::class -> EmailPremium(email.emailPattern() && password.isEmptyPattern())
            EmailRegular::class -> EmailRegular(email.emailPattern() && password.isEmptyPattern())
            else -> throw IllegalStateException("Unknown")
        }
    }
}

fun main() {
    val emailPremium by lazy { LoginFactory.requestLogin<EmailPremium>(email = "premium@gmail.com", password = "Premium").validate }
    val emailRegular by lazy { LoginFactory.requestLogin<EmailRegular>(email = "regular@gmail.com", password = "Regular").validate }

    Assertions.assertEquals(emailPremium, true)
    Assertions.assertTrue(emailPremium, "Assert Equal Premium")

    Assertions.assertEquals(emailRegular, true)
    Assertions.assertTrue(emailRegular, "Assert Equal Regular")
}