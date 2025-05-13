package com.tecmov2025.manoslocales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecmov2025.manoslocales.ui.theme.ManosLocalesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManosLocalesTheme {
                val navController = rememberNavController()
                val viewModel = ProductViewModel()
                MainNav(navController, viewModel)
           }
        }
    }

    @Composable
    fun MainNav(navController: NavHostController, viewModel: ProductViewModel)
    {
        NavHost(
            navController = navController,
            startDestination = "MainScreen"
        )
        {
            composable("ProductoScreen") {ProductScreen(viewModel)}
            composable("ConfigScreen"){ConfigScreen(navController)}
            composable("PerfilScreen"){ PerfilForm()}
            composable("MainScreen") { CustomScaffold(navController,viewModel)
        }
    }

    }
}

