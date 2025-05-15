package com.tecmov2025.manoslocales.ActivityLogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.ui.theme.ManosLocalesTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManosLocalesTheme {
                val navController = rememberNavController()
                LoginNav(navController)

            }
        }
    }

    @Composable
    fun LoginNav(navController: NavHostController)
    {
        NavHost(
            navController = navController,
            startDestination = "SplashScreen"
        )
        {
            composable("Login") { LoginScreen(navController) }
            composable("SplashScreen") { SplashScreen(navController) }
            composable("RegisterScreen") { RegisterScreen() }
            composable("PasswordScreen") { PasswordScreen() }
        }
    }

}