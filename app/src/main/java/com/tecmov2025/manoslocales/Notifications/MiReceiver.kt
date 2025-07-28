package com.tecmov2025.manoslocales.Notifications

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission

class MiReceiver: BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(ctx: Context, intent: Intent) {
        NotificationHandler.mostrarNotificacionRandom(ctx)
    }
}
