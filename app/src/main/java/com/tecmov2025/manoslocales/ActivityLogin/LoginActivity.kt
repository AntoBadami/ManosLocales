package com.tecmov2025.manoslocales.ActivityLogin

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tecmov2025.manoslocales.BiometricSensor.BiometricHandler
import com.tecmov2025.manoslocales.Database.AppDatabase
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.RetrofitClient
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.ui.theme.ManosLocalesTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ProductViewModel(ApiRepository(RetrofitClient.apiService,AppDatabase.obtenerInstancia(applicationContext)))

        // Sensor biometrico
        BiometricHandler.setBaseActivity(this)

        setContent {
            ManosLocalesTheme {LoginNavigation(viewModel)}
        }
    }


}