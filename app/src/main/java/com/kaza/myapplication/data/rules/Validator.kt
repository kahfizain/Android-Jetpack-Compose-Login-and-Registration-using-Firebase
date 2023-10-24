package com.kaza.myapplication.data.rules

object Validator {

    fun validateFirsName(fName: String): ValidationResult {
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length >= 6)
        )

    }

    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length >= 4)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrEmpty() && isValidEmail(email))
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )
    }

}


data class ValidationResult(
    val status: Boolean = false
)

fun isValidEmail(email: String): Boolean {
    val emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"
    return email.matches(emailPattern.toRegex())
}