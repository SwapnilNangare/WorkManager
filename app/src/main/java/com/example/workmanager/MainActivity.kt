package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // simpleWork()

        myWorkManager()
    }

    private fun myWorkManager() {
        val constrains = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresBatteryNotLow(true)
            .build()

        val myRequest = PeriodicWorkRequest.Builder(
            MyWorker::class.java,
            15, TimeUnit.MINUTES
        ).setConstraints(constrains).build()

        WorkManager.getInstance(this)
            .enqueue()
    }

    private fun simpleWork() {

        //we create one time Request
        val mRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .build()

        WorkManager.getInstance(this)
            .enqueue(mRequest)
    }
}