package com.antareza.tesdanamon.util

import android.content.Context
import android.content.SharedPreferences
import com.antareza.tesdanamon.data.reqres.model.User

class SharedPref(val context: Context) {
    private val isSessionUser: SharedPreferences

    init {
        isSessionUser = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveIsLogIn(isLogIn: Boolean) {
        val editor = isSessionUser.edit()
        editor.putBoolean("isLogIn", isLogIn)
        editor.commit()
        editor.apply()
    }

    fun saveDataUser(user: User) {
        val editor = isSessionUser.edit()
        editor.putInt(ID, user.id)
        editor.putString(USERNAME, user.username)
        editor.putString(EMAIL, user.email)
        editor.putString(PASSWORD, user.password)
        editor.putInt(ROLE, user.role)
        editor.commit()
        editor.apply()
    }

    fun getIsLogIn(): Boolean {
        return isSessionUser.getBoolean("isLogIn", false)
    }

    fun getDataUser(): User {
        return User(
            isSessionUser.getInt(ID, 0),
            isSessionUser.getString(USERNAME, "") ?: "",
            isSessionUser.getString(EMAIL, "") ?: "",
            isSessionUser.getString(PASSWORD, "") ?: "",
            isSessionUser.getInt(ROLE, 0),
        )
    }

    fun logout() {
        val editor = isSessionUser.edit()
        editor.clear()
        editor.commit()
        editor.apply()
    }

    companion object {
        const val PREFS_NAME = "user_session"
        const val ID = "id"
        const val USERNAME = "username"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val ROLE = "role"
        const val SUCCESS = "success"
    }

}