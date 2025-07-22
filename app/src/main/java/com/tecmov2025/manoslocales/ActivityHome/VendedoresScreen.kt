package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.Utils.BarraDeBusqueda
import com.tecmov2025.manoslocales.Utils.ExamplesVendedoresList
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Vendedor
import com.tecmov2025.manoslocales.Utils.VendedorCard
import androidx.compose.foundation.lazy.items

@Composable
fun VendedoresScreen(viewModel: ProductViewModel, navController: NavController) {
    BarraDeBusqueda(navController = navController, viewModel = viewModel) { padding, _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // respeta top bar y bottom bar
                .padding(16.dp)
        ) {
            VendedoresScreenBody(padding, navController)
        }
    }
}

@Composable
fun VendedoresScreenBody(paddingBarraDeBusqueda: PaddingValues, navController: NavController) {
    val vendedoresList: List<Vendedor> = ExamplesVendedoresList().vendedoresList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = paddingBarraDeBusqueda.calculateTopPadding())
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(vendedoresList) { vendedor ->
                VendedorCard(
                    vendedor = vendedor,
                    navController = navController
                )
            }
        }
    }
}
