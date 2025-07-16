package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.Utils.BarraDeBusqueda
import com.tecmov2025.manoslocales.Utils.ProductViewModel

@Composable
fun VendedoresScreen(viewModel: ProductViewModel, navController: NavController) {
    BarraDeBusqueda(navController = navController, viewModel = viewModel) { padding, _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // respeta top bar y bottom bar
                .padding(16.dp)
        ) {
            Text("Pantalla de Vendedores")
            // Agregá acá lo que necesites mostrar
        }
    }
}
