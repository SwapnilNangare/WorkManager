package com.example.workmanager

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {


    override fun doWork(): Result {
        Log.d("Do work success", "doWork success function called")
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivities(
            applicationContext, 0, intent, 0,
        )
        val builder = NotificationCompat.Builder(applicationContext, "my_unique_id")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Hello swapnil")
            .setContentTitle("Life is good")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(1, builder.build())
        }

    }
}