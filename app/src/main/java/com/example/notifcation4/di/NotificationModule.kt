package com.example.notifcation4.di

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.notifcation4.MainActivity
import com.example.notifcation4.R
import com.example.notifcation4.navcation.MY_ARG
import com.example.notifcation4.navcation.MY_URI
import com.example.notifcation4.recever.MyReciver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager {
        return ContextCompat.getSystemService(context, NotificationManager::class.java)!!
    }

    @SuppressLint("LaunchActivityFromNotification")
    @Provides
    fun provideNotificationCompatBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        val channelId = "default_channel"
        val intent = Intent(
            context, MyReciver::class.java
        ).apply {
            putExtra("massg", "Click")
        }
        val intentpending = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )


        val intantactivty = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/$MY_ARG=$MY_ARG".toUri(),
            context,
            MainActivity::class.java
        )

        val pedningActivty: PendingIntent =
            TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(intantactivty)
                getPendingIntent(1,PendingIntent.FLAG_IMMUTABLE)!!
            }






        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Mohamed Channel"
            val descriptionText = "Mohamed Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                ContextCompat.getSystemService(context, NotificationManager::class.java)!!
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(context, channelId)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                R.drawable.baseline_notifications_24
            ))
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(
                0,"Action",intentpending
            )
            .setContentIntent(pedningActivty)

    }
}