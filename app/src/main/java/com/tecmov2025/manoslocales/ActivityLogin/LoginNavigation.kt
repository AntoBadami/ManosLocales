package com.tecmov2025.manoslocales.ActivityLogin

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.ActivityFavoritos.FavoritosScreen
import com.tecmov2025.manoslocales.ActivityHome.ConfigScreen
import com.tecmov2025.manoslocales.ActivityHome.PerfilScreen
import com.tecmov2025.manoslocales.Utils.ProductScreen
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Screens

@Composable
fun LoginNavigation()
{
    val navController = rememberNavController()
    //val viewModel = ProductViewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    )
    {
        composable(Screens.LoginScreen.route) { LoginScreen(navController) }
        composable(Screens.SplashScreen.route) { SplashScreen(navController) }
        composable(Screens.RegisterScreen.route) { RegisterScreen() }
        composable(Screens.PasswordScreen.route) { PasswordScreen() }
    }
}