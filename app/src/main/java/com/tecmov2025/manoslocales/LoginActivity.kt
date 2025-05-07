package com.tecmov2025.manoslocales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Nav(navController)
        }
    }

    @Composable
    fun Nav(navController: NavHostController)
    {

        NavHost(
            navController = navController,
            startDestination = "SplashScreen")
        {
            composable("Login") { Login(navController) }
            composable("SplashScreen") { SplashScreen(navController) }
            composable ("RegisterScreen"){ RegisterForm() }
        }
    }

}
