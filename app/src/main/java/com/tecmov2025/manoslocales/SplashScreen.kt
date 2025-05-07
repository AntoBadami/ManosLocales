package com.tecmov2025.manoslocales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

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
        delay(500)
        navController.navigate("Login")
    }
}