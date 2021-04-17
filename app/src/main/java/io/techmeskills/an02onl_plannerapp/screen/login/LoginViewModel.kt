package io.techmeskills.an02onl_plannerapp.screen.login

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import io.techmeskills.an02onl_plannerapp.User
import io.techmeskills.an02onl_plannerapp.model.dao.NotesDao
import io.techmeskills.an02onl_plannerapp.support.CoroutineViewModel
import kotlinx.coroutines.launch


class LoginViewModel(private val sharedPreferences: SharedPreferences, private val notesDao: NotesDao) : CoroutineViewModel() {

    fun getSavedUser(): User? {
        if (sharedPreferences.contains(FIRST_NAME) && sharedPreferences.contains(LAST_NAME)) {
            val fn = sharedPreferences.getString(FIRST_NAME, null)
            val ln = sharedPreferences.getString(LAST_NAME, null)
            val id = sharedPreferences.getLong(USER_ID, 0)

            return User(id, fn!!, ln!!)
        }
        return null
    }

    fun setSavedUser(user: User){
        launch {
            val editor: Editor = sharedPreferences.edit()

            editor.putString(FIRST_NAME, user.firstName)
            editor.putString(LAST_NAME, user.lastName)
            editor.putLong(USER_ID, notesDao.saveUser(user))
            editor.apply()
        }
    }

    companion object{
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val USER_ID = "userId"
    }

    fun clearSavedUser(){
        launch {

            sharedPreferences.edit().clear().apply()
        }
    }
}