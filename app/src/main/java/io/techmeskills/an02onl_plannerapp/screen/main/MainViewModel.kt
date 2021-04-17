package io.techmeskills.an02onl_plannerapp.screen.main

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import io.techmeskills.an02onl_plannerapp.Note
import io.techmeskills.an02onl_plannerapp.User
import io.techmeskills.an02onl_plannerapp.UserWithNotes
import io.techmeskills.an02onl_plannerapp.model.dao.NotesDao
import io.techmeskills.an02onl_plannerapp.screen.login.LoginViewModel
import io.techmeskills.an02onl_plannerapp.support.CoroutineViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val sharedPreferences: SharedPreferences, private val notesDao: NotesDao) : CoroutineViewModel() {


    val notesLiveData = notesDao.getAllUserNotes(getSavedUser()!!.userId).map { listOf(AddNote) + it }.flowOn(Dispatchers.IO).asLiveData()


    fun deleteNote(note: Note) {
        launch {
            notesDao.deleteNote(note)
        }
    }


    fun updateTwoNote(note1: Note, note2: Note) {
        launch {
            notesDao.updateNote(note1)
            notesDao.updateNote(note2)
        }
    }

    private fun getSavedUser(): User? {
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


object AddNote : Note(-1, "", user = -1)





