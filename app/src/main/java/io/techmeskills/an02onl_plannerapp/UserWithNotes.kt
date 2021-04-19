package io.techmeskills.an02onl_plannerapp

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import kotlinx.parcelize.Parcelize


@Parcelize
class UserWithNotes(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "user"
    , entity = Note::class)
    val notes: List<Note>
) : Parcelable


