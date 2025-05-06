package com.tecmov2025.manoslocales

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
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

        NavHost(navController = navController, startDestination = "SplashScreen")
        {
            composable("Login")
            {
                Login()
            }
            composable("SplashScreen")
            {
                SplashScreen(navController)
            }
        }
    }

    @Composable
    fun SplashScreen(navController: NavHostController)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ){
          Text("Manos Locales")
        }
        LaunchedEffect(Unit)
        {
            delay(3000)
            navController.navigate("Login")
        }
    }

    @Composable
    fun Login()
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment
                .CenterHorizontally,
            verticalArrangement = Arrangement
                .Center
        ) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            TextField (
                value = username,
                onValueChange = { username = it }
            )
            TextField (
                value = password,
                onValueChange = { password = it }
            )
            Button(onClick = {}) {
                Text("iniciar sesion")
            }

        }

    }

}
