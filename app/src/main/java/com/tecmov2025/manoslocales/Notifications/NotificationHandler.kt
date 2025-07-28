package com.tecmov2025.manoslocales.Notifications

import android.Manifest
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.tecmov2025.manoslocales.ActivityLogin.LoginActivity
import com.tecmov2025.manoslocales.SharedPreferences.CONFIG_TIEMPO
import java.util.concurrent.TimeUnit
import kotlin.jvm.java
import kotlin.random.Random


object NotificationHandler {


    // Canales
    private val CHANNEL_ID = "general"
    private val CHANNEL_NAME = "Canal General"
    private val CHANNEL_DESC = "Notificaciones generales de la app"

    private val notificacionesPersonalizadas =  mutableListOf<Pair<Int, Notification>>()

    fun addNotification(title: String,text: String, context: Context, id: Int)
    {
        val clickIntent = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val clickPI = PendingIntent.getActivity(
            context,
            0,
            clickIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val nuevaNotificacion = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_notification_clear_all)  // Icono que verá el usuario
            .setContentTitle(title)   // Título principal
            .setContentText(text) // Texto descriptivo
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)      // Prioridad (API < 26)
            .setContentIntent(clickPI)                             // Asocia el PendingIntent al toque
            .setAutoCancel(true)                                   // Se cierra al tocarla
            .build()

        notificacionesPersonalizadas.add(id to nuevaNotificacion)

    }


    fun createChannel(context : Context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESC
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun setPeriodicNotificationTime(context: Context, periodo_milis : CONFIG_TIEMPO = CONFIG_TIEMPO.H6)
    {
        val intent = Intent(context, MiReceiver::class.java)
        val pending = PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_MUTABLE)

        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // cancela, si la hubiese, alarmas anteriores
        alarmMgr.cancel(pending)

        if (periodo_milis != CONFIG_TIEMPO.NUNCA)
        {
            // Tiempo del primer disparo (ahora + x tiempo
            val firstTrigger = System.currentTimeMillis() + calcularTiempoNotificaciones(periodo_milis)

            // Intervalo de repetición (cada x periodo)
            val interval = calcularTiempoNotificaciones(periodo_milis)

            alarmMgr.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,  // tipo: usa reloj de pared y despierta el dispositivo
                firstTrigger,             // primer disparo
                interval,                 // después repite cada interval milis
                pending                   // pending intent a enviar cada vez
            )
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun buildGeneralNotification(context: Context)
    {
        val clickIntent = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val clickPI = PendingIntent.getActivity(
            context,
            0,
            clickIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_notification_clear_all)  // Icono que verá el usuario
            .setContentTitle("¡Productos nuevos te estan esperando!")   // Título principal
            .setContentText("Es hora de revisar la tienda!") // Texto descriptivo
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)      // Prioridad (API < 26)
            .setContentIntent(clickPI)                             // Asocia el PendingIntent al toque
            .setAutoCancel(true)                                   // Se cierra al tocarla
            .build()

        NotificationManagerCompat.from(context)
            .notify(1001, notification)  // 1001 es el ID único de esta notificación

    }

    /**
     * Funcion auxiliar de muestra de app
     */
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun mostrarNotificacionRandom(context: Context) {
        Log.d("Debug","Intentando enviar notificaciones")
        if (notificacionesPersonalizadas.isEmpty()) buildGeneralNotification(context)
        else
        {
            val (id, notification) = notificacionesPersonalizadas.random()

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(id, notification)
        }
    }

    fun cancelarNotificacion(context: Context, id: Int) {
        NotificationManagerCompat.from(context).cancel(id)
        notificacionesPersonalizadas.removeIf { it.first == id }
    }

    fun calcularTiempoNotificaciones(periodo: CONFIG_TIEMPO):Long
    {
        return when(periodo)
        {
            CONFIG_TIEMPO.TEST->5_000L
            CONFIG_TIEMPO.H6 -> TimeUnit.HOURS.toMillis(6)
            CONFIG_TIEMPO.D1 -> TimeUnit.DAYS.toMillis(1)
            CONFIG_TIEMPO.D2 -> TimeUnit.DAYS.toMillis(2)
            CONFIG_TIEMPO.S1 -> TimeUnit.DAYS.toMillis(7)
            else -> return 0
        }


    }
}