package io.techmeskills.an02onl_plannerapp.screen.add

import android.content.SharedPreferences
import io.techmeskills.an02onl_plannerapp.model.dao.NotesDao
import io.techmeskills.an02onl_plannerapp.Note
import io.techmeskills.an02onl_plannerapp.User
import io.techmeskills.an02onl_plannerapp.model.sharedPrefs.SharPrefUser
import io.techmeskills.an02onl_plannerapp.screen.login.LoginViewModel
import io.techmeskills.an02onl_plannerapp.support.CoroutineViewModel
import kotlinx.coroutines.launch

class AddViewModel(private val sharPrefUser: SharPrefUser, private val notesDao: NotesDao) : CoroutineViewModel() {
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

    fun getUser(): User? {
        return sharPrefUser.getSavedUser()
    }
}