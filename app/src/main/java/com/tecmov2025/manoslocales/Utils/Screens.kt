package com.tecmov2025.manoslocales.Utils


/**
 * Clase sellada para navegacion entre pantallas
 */
sealed class Screens(val route: String)
{
    object PerfilScreen: Screens("PerfilScreen")
    object ProductoScreen: Screens("ProductoScreen")
    object ConfigScreen: Screens("ConfigScreen")
    object MainScreen: Screens("MainScreen")
    object FavoritosScreen: Screens("FavoritosScreen")
    object RegisterScreen : Screens("RegisterScreen")
    object SplashScreen : Screens("SplashScreen")
    object LoginScreen : Screens("LoginScreen")
    object PasswordScreen : Screens("PasswordScreen")
}