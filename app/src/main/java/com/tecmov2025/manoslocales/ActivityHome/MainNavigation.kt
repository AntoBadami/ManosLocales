package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.Database.AppDatabase
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.RetrofitClient
import com.tecmov2025.manoslocales.Utils.ExampleProductList
import com.tecmov2025.manoslocales.Utils.ProductScreen
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Screens
import com.tecmov2025.manoslocales.Utils.VendedorScreen

/**
 * Gestinona la navegacion en la activity Main
 */
@Composable
fun MainNavigation(viewModel: ProductViewModel)
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    )
    {
        composable(Screens.ConfigScreen.route){ConfigScreen()}
        composable(Screens.PerfilScreen.route){ PerfilScreen()}
        composable(Screens.MainScreen.route) { MainScreen(viewModel, navController) }
        composable(Screens.ProductoScreen.route){ProductScreen(viewModel, navController)}
        composable(Screens.VendedoresScreen.route) { VendedoresScreen(viewModel, navController) }
        composable(Screens.VendedorScreen.route){VendedorScreen(viewModel, navController) }

    }
}