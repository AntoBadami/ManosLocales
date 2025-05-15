package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.Utils.ProductScreen
import com.tecmov2025.manoslocales.Utils.ProductViewModel

@Composable
fun MainNavigation()
{
    val navController = rememberNavController()
    val viewModel = ProductViewModel()
    NavHost(
        navController = navController,
        startDestination = MainScreens.MainScreen.route
    )
    {
        composable(MainScreens.ProductoScreen.route) { ProductScreen(viewModel) }
        composable(MainScreens.ConfigScreen.route){ConfigScreen()}
        composable(MainScreens.PerfilScreen.route){ PerfilForm()}
        composable(MainScreens.MainScreen.route) { CustomScaffold(navController,viewModel) }
    }
}