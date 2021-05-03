package io.techmeskills.an02onl_plannerapp.model.chainModules

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import io.techmeskills.an02onl_plannerapp.model.User
import io.techmeskills.an02onl_plannerapp.model.dao.UsersDao
import io.techmeskills.an02onl_plannerapp.model.preferences.SettingsStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ChainUserModule(
    context: Context,
    private val usersDao: UsersDao,
    private val settingsStore: SettingsStore,
) {

    val allUserNames = usersDao.getAllUsers()

    suspend fun login(firsName: String, lastName: String) {
        withContext(Dispatchers.IO) {
            val userId: Long = if (checkUserExists(firsName, lastName).not()) {
                usersDao.saveUser(User(name = firsName))
            } else {
                usersDao.getUserId(firsName)

            }
            settingsStore.setUser(User(userId, firsName))
        }
    }

    private suspend fun checkUserExists(firsName: String, lastName: String): Boolean {
        return withContext(Dispatchers.IO) {
            usersDao.getUserId(firsName) > 0
        }
    }

    fun checkUserLoggedIn(): Flow<Boolean> =
        settingsStore.storedUserFlow().map { it.name.isNotEmpty() }.flowOn(Dispatchers.IO)

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            settingsStore.setUser(User(name = ""))
        }
    }

    fun getCurrentUser(): Flow<User> {
        return settingsStore.storedUserFlow()
    }

    suspend fun updtCurrUser(newName: String) {
        val curUser = settingsStore.getUser()
        val newUser = User(curUser.id, newName, curUser.passwd)
        usersDao.updateUser(newUser)
        settingsStore.setUser(newUser)
    }

    suspend fun delCurrUser() {
        withContext(Dispatchers.IO) {
            usersDao.deleteUser(settingsStore.getUser())
        }

    }

    @ExperimentalCoroutinesApi
    fun getCurrentUserFlow(): Flow<User> = settingsStore.storedUserFlow().flatMapLatest {
        usersDao.getByName(it.name)
    }

    @SuppressLint("HardwareIds")
    val phoneId: String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}