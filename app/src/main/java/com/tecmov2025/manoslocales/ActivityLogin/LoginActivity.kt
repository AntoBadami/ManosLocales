package com.tecmov2025.manoslocales.ActivityLogin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tecmov2025.manoslocales.BiometricSensor.BiometricHandler
import com.tecmov2025.manoslocales.Database.AppDatabase
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.RetrofitClient
import com.tecmov2025.manoslocales.Notifications.NotificationHandler
import com.tecmov2025.manoslocales.SharedPreferences.CONFIG_TIEMPO
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.ui.theme.ManosLocalesTheme


class LoginActivity : AppCompatActivity() {
    companion object { private const val REQ_NOTIF = 100 }
    private lateinit var viewModel: ProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ProductViewModel(
            ApiRepository(
                RetrofitClient.apiService,AppDatabase.obtenerInstancia(applicationContext)))

        // Comprueba y pide permiso de notificaciones si hace falta
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) { requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQ_NOTIF) }


        // Sensor biometrico
        BiometricHandler.setBaseActivity(this)


        enableEdgeToEdge()

        setContent {
            ManosLocalesTheme {LoginNavigation(viewModel)}
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Notificaciones
        if (requestCode == REQ_NOTIF) {
            if ((grantResults.firstOrNull() ?: PackageManager.PERMISSION_DENIED)
                == PackageManager.PERMISSION_GRANTED)
            { // Permiso concedido
                NotificationHandler.createChannel(this)
                viewModel.establecerTiempoNotificaciones(this)
            }
            else
            {
                viewModel.establecerTiempoNotificaciones(this, CONFIG_TIEMPO.NUNCA)
            }
        }
    }

}
