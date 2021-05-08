package io.techmeskills.an02onl_plannerapp.model.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import io.techmeskills.an02onl_plannerapp.R
import org.koin.experimental.builder.getArguments


class MyAlarmManager {


    fun setAlarm(context: Context, alarmTime: Long, message: String) {

        //Intent
        val intent = Intent(context, MyAlarmManager::class.java)
        intent.putExtra("ALARM_MSG", message); //put our info in intent

        //PendingIntent
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        //Alarm manager
        val alarmManager = getSystemService(context, AlarmManager::class.java) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC, alarmTime, pendingIntent)
//        Toast.makeText(context, "Alarm is set", Toast.LENGTH_SHORT).show()

        //Notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                CHANNEL_ID,
                MY_APP_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(mChannel)
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