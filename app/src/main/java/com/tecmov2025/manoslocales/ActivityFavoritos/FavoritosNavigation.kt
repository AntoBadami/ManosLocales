package com.tecmov2025.manoslocales.ActivityFavoritos

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.ActivityHome.ConfigScreen
import com.tecmov2025.manoslocales.ActivityHome.MainScreen
import com.tecmov2025.manoslocales.ActivityHome.PerfilScreen
import com.tecmov2025.manoslocales.Utils.ProductScreen
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Screens

/**
 * Gestinona la navegacion en la activity Favoritos
 */
@Composable
fun FavoritosNavigation()
{
    val navController = rememberNavController()
    val viewModel = ProductViewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.FavoritosScreen.route
    )
    {
        composable(Screens.ProductoScreen.route) { ProductScreen(viewModel) }
        composable(Screens.FavoritosScreen.route){ FavoritosScreen(viewModel = viewModel, navController = navController) }
        composable(Screens.ConfigScreen.route) {ConfigScreen()}
        composable(Screens.PerfilScreen.route){PerfilScreen()}
    }
}