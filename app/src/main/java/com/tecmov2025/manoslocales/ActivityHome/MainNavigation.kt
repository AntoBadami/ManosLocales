package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.Utils.ExampleProductList
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
        composable(Screens.ConfigScreen.route){ConfigScreen()}
        composable(Screens.PerfilScreen.route){ PerfilScreen()}
        composable(Screens.MainScreen.route) { MainScreen(viewModel, navController) }
        composable(Screens.VendedoresScreen.route) { VendedoresScreen(viewModel, navController) }
        composable(
            route = "ProductoScreen/{nombre}"
        ) { backStackEntry ->
            val nombreProducto = backStackEntry.arguments?.getString("nombre") ?: return@composable

            // Buscá el producto en la lista
            val producto = ExampleProductList().productosList.find { it.nombre == nombreProducto }

            // Si lo encuentra, lo seleccionás en el viewModel
            producto?.let {
                viewModel.seleccionarProducto(it)
                ProductScreen(viewModel, navController)
            }
        }

    }
}