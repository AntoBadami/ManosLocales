package com.tecmov2025.manoslocales.ActivityHome

import com.tecmov2025.manoslocales.Utils.ProductScreen

/**
 * Clase sellada para navegacion entre pantallas
 */
sealed class MainScreens(val route: String)
{
    object PerfilScreen: MainScreens("PerfilScreen")
    object ProductoScreen: MainScreens("ProductoScreen")
    object ConfigScreen: MainScreens("ConfigScreen")
    object MainScreen: MainScreens("MainScreen")
}