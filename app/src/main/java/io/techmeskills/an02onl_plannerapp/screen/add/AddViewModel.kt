package io.techmeskills.an02onl_plannerapp.screen.add

import android.content.SharedPreferences
import io.techmeskills.an02onl_plannerapp.model.dao.NotesDao
import io.techmeskills.an02onl_plannerapp.Note
import io.techmeskills.an02onl_plannerapp.User
import io.techmeskills.an02onl_plannerapp.screen.login.LoginViewModel
import io.techmeskills.an02onl_plannerapp.support.CoroutineViewModel
import kotlinx.coroutines.launch

class AddViewModel(private val sharedPreferences: SharedPreferences, private val notesDao: NotesDao) : CoroutineViewModel() {
    fun addNewNote(note: Note) {
        launch {
            notesDao.saveNote(note)
        }
    }

    fun updateNote(note: Note) {
        launch {
            notesDao.updateNote(note)
        }
    }

    fun getSavedUser(): User? {
        if (sharedPreferences.contains(LoginViewModel.FIRST_NAME) && sharedPreferences.contains(
                LoginViewModel.LAST_NAME
            )) {
            val fn = sharedPreferences.getString(LoginViewModel.FIRST_NAME, null)
            val ln = sharedPreferences.getString(LoginViewModel.LAST_NAME, null)
            val id = sharedPreferences.getLong(LoginViewModel.USER_ID, 0)

            return User(id, fn!!, ln!!)
        }
        return null
    }
}