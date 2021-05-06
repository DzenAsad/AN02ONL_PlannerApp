package io.techmeskills.an02onl_plannerapp.model.alarm

import android.app.*
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.techmeskills.an02onl_plannerapp.R
import android.app.NotificationManager
import androidx.core.content.ContextCompat.getSystemService


class MyAlarmManager {


    fun setAlarm(context: Context, alarmTime: Long, message: String) {
        lateinit var mChannel: NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = NotificationChannel(CHANNEL_ID, MY_APP_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(mChannel)
        }


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_note_24)
            .setContentTitle("Напоминание")
            .setContentText("Пора покормить кота")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
            // посылаем уведомление
        }
    }


    fun cancelAlarm(context: Context) {

    }


    companion object {
        const val NOTIFICATION_ID = 0
        const val CHANNEL_ID = "channelID"
        const val MY_APP_CHANNEL_NAME = "MyApp"
    }
}