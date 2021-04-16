package io.techmeskills.an02onl_plannerapp

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
open class Note(
    @Embedded val user: UserAccess,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val date: String? = null,

) : Parcelable