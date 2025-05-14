package com.tecmov2025.manoslocales.ActivityLogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tecmov2025.manoslocales.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController)
{
    Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .wrapContentSize())
    {
        Image (
            modifier = Modifier
                .size(350.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Fit
        )

    }
    LaunchedEffect(Unit)
    {
        delay(500)
        navController.navigate("Login") {
            popUpTo("SplashScreen") { inclusive = true }
        }
    }
}