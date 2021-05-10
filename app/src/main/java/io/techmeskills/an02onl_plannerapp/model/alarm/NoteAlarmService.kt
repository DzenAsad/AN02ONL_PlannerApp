package io.techmeskills.an02onl_plannerapp.model.alarm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.techmeskills.an02onl_plannerapp.model.modules.NoteModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class NoteAlarmService : Service(), KoinComponent {
    private val noteModule: NoteModule by inject()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val noteId =
                it.getLongExtra("NoteDeleteId", -1)
            when (it.action) {
                "NoteDelete" -> {
                    GlobalScope.launch {
                        noteModule.deleteNoteById(noteId)
                    }
                }
                else -> Unit
            }
            stopSelf()
        }
        return START_NOT_STICKY
    }
}