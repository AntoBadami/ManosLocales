package com.tecmov2025.manoslocales.ActivityLogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.BiometricSensor.BiometricHelper
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
        val promptInfo = BiometricHelper.buildPromptInfo()
        val biometricPrompt = BiometricHelper.createPrompt(
            this,
            onSuccess = { goToHome(this) },
            onError   = { finish() },
            onFailure = {  }
        )
        setContent {
            ManosLocalesTheme {LoginNavigation(viewModel,
            {
                if (BiometricHelper.canAuthenticate(this)) {
                biometricPrompt.authenticate(promptInfo)
            }})}
        }
    }


}