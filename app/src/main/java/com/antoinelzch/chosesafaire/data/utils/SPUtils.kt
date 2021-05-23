package com.antoinelzch.chosesafaire.data.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SPUtils {
    private const val USER_FIRSTNAME = "firstname"
    private const val USER_LASTNAME = "lastname"
    private const val PREFERENCE_ID = "app.SharedPreferences"

    private var sharedPreferences: SharedPreferences? = null

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_ID, MODE_PRIVATE)
    }

    fun reset() {
        sharedPreferences?.edit()?.remove(USER_FIRSTNAME)?.apply()
        sharedPreferences?.edit()?.remove(USER_LASTNAME)?.apply()
    }

    fun getLastName(): String? {
        return sharedPreferences?.getString(USER_LASTNAME, null)
    }

    fun getFirstName(): String? {
        return sharedPreferences?.getString(USER_FIRSTNAME, null)
    }

    fun setFirstName(firstName: String?) {
        sharedPreferences?.edit()?.putString(USER_FIRSTNAME, firstName)?.apply()
    }

    fun setLastName(lastName: String?) {
        sharedPreferences?.edit()?.putString(USER_LASTNAME, lastName)?.apply()
    }

    fun setBoth(firstName: String?, lastName: String?) {
        sharedPreferences?.edit()?.putString(USER_LASTNAME, lastName)?.apply()
        sharedPreferences?.edit()?.putString(USER_FIRSTNAME, firstName)?.apply()
    }
}