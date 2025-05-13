package com.tecmov2025.manoslocales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.ui.theme.ManosLocalesTheme

class FavoritosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManosLocalesTheme{
                val viewModel = ProductViewModel()
                val navController = rememberNavController()
                Nav(navController,viewModel)
            }
        }
    }

    @Composable
    fun Nav(navController: NavHostController,viewModel: ProductViewModel) {
        NavHost(
            navController = navController,
            startDestination = "FavoritosScreen"
        )
        {
            composable("FavoritosScreen") { FavoritosScreen(navController,viewModel) }
            composable("ProductoScreen") { ProductScreen(viewModel) }
        }
    }

}
