package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.Utils.ProductScreen
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Screens

/**
 * Gestinona la navegacion en la activity Main
 */
@Composable
fun MainNavigation()
{
    val navController = rememberNavController()
    val viewModel = ProductViewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    )
    {
        composable(Screens.ProductoScreen.route) { ProductScreen(viewModel) }
        composable(Screens.ConfigScreen.route){ConfigScreen()}
        composable(Screens.PerfilScreen.route){ PerfilScreen()}
        composable(Screens.MainScreen.route) { MainScreen(viewModel, navController) }
    }
}