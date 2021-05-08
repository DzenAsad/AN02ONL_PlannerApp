package io.techmeskills.an02onl_plannerapp.model.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.techmeskills.an02onl_plannerapp.R

class NoteAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val message = intent.getStringExtra("ALARM_MSG")  //get info from intent

        //Create notification
        val builder = NotificationCompat.Builder(context, MyAlarmManager.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_note_24)
            .setContentTitle("Напоминание")
            .setContentText(message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        //Push notification
        with(NotificationManagerCompat.from(context)) {
            notify(MyAlarmManager.NOTIFICATION_ID, builder.build())
            // посылаем уведомление
        }
    }
}