package io.techmeskills.an02onl_plannerapp.model.sharedPrefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import io.techmeskills.an02onl_plannerapp.User

class SharPrefUser(app: Application) {

    private val sharedPreferences: SharedPreferences =
        app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    fun getSavedUser(): User? {
        if (sharedPreferences.contains(FIRST_NAME) && sharedPreferences.contains(LAST_NAME)) {
            val fn = sharedPreferences.getString(FIRST_NAME, null)
            val ln = sharedPreferences.getString(LAST_NAME, null)
            val id = sharedPreferences.getLong(USER_ID, 0)

            return User(id, fn!!, ln!!)
        }
        return null
    }

    fun setSavedUser(user: User, userGetOrPut: (User) -> Long) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(FIRST_NAME, user.firstName)
        editor.putString(LAST_NAME, user.lastName)
        editor.putLong(USER_ID, userGetOrPut(user))
        editor.apply()
    }


    fun clearSavedUser() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        
        private const val FIRST_NAME = "firstName"
        private const val LAST_NAME = "lastName"
        private const val USER_ID = "userId"
        
        private const val PREFERENCES_FILE_KEY =
            "io.techmeskills.an02onl_plannerapp.settings_preferences"
    }
}