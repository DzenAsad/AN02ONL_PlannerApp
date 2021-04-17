package io.techmeskills.an02onl_plannerapp.model.dao

import androidx.room.*
import io.techmeskills.an02onl_plannerapp.Note
import io.techmeskills.an02onl_plannerapp.User
import io.techmeskills.an02onl_plannerapp.UserWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveNote(note: Note): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveNotes(notes: List<Note>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateNotes(notes: List<Note>)

    @Delete
    abstract fun deleteNote(note: Note)

    @Delete
    abstract fun deleteNotes(notes: List<Note>)

    @Transaction
    @Query("SELECT * FROM notes, users")
    abstract fun getAllUserWithNotes(): Flow<List<UserWithNotes>>

    @Transaction
    @Query("SELECT * FROM notes WHERE user == :id")
    abstract fun getAllUserNotes(id: Long): Flow<List<Note>>

    @Query("SELECT user_id FROM users WHERE first_name == :firstName and last_name == :lastName")
    abstract fun getUserId(firstName: String, lastName: String): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveUser(users: List<User>): List<Long>

}