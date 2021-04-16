package io.techmeskills.an02onl_plannerapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users", indices = [Index(value = ["firstName", "lastName"], unique = true)])
class UserAccess (
    @PrimaryKey(autoGenerate = true) val userId: Long = 0L,
    val firstName: String,
    val lastName: String
) : Parcelable