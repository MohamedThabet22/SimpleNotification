package com.example.notifcation4



import android.app.Application
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val notificationManager: NotificationManager,
    private val notificationBuilder: NotificationCompat.Builder,
    private val context: Application
) : AndroidViewModel(context) {

    fun showNotification() {
        val notificationId = 1
        val notification = notificationBuilder
            .setContentTitle("Notification Title")
            .setContentText("HI there is to New Views")

            .build()

        notificationManager.notify(notificationId, notification)
    }

    fun  updateNotifivation(title : String , name : String){
        val notficationId = 1
        val notification = notificationBuilder.
                setContentTitle(title)
            .setContentText(name)
            .build()
        notificationManager.notify(notficationId,notification)
    }
    fun  cancel(){
        notificationManager.cancel(1)
    }

}