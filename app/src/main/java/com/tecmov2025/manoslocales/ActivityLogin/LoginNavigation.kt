package com.tecmov2025.manoslocales.ActivityLogin

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Screens

@Composable
fun LoginNavigation(viewModel: ProductViewModel)
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    )
    {
        composable(Screens.LoginScreen.route) { LoginScreen(navController,viewModel) }
        composable(Screens.SplashScreen.route) { SplashScreen(navController,viewModel) }
        composable(Screens.RegisterScreen.route) { RegisterScreen() }
        composable(Screens.PasswordScreen.route) { PasswordScreen() }
    }
}