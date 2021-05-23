package com.antoinelzch.chosesafaire.data.utils

import androidx.core.util.PatternsCompat

object ValidationUtils {
    fun isEmailValid(email:String): Boolean = email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password:String): Boolean = password.isNotEmpty() && password.length >= 4

    fun isFirstNameNotEmptyOrBlank(firstName: String): Boolean = firstName.isNotEmpty() && firstName.isNotBlank()

    fun isLastNameNotEmptyOrBlank(lastName: String): Boolean = lastName.isNotEmpty() && lastName.isNotBlank()
}