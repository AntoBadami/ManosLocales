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
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.VendedorCard
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp

@Composable
fun VendedoresScreen(viewModel: ProductViewModel, navController: NavController) {
    BarraDeBusqueda(navController = navController, viewModel = viewModel) { padding, _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // respeta top bar y bottom bar
                .padding(16.dp)
        ) {
            VendedoresScreenBody(padding, navController, viewModel)
        }
    }
}

@Composable
fun VendedoresScreenBody(paddingBarraDeBusqueda: PaddingValues, navController: NavController,viewModel: ProductViewModel) {

    val vendedoresFiltradosPorBusqueda by viewModel.vendedoresBuscados.collectAsState()
    val todosLosVendedores by viewModel.vendedoresState.collectAsState()
    val busqueda by viewModel.busqueda.collectAsState()

    val vendedoresList = if (busqueda.isBlank()) todosLosVendedores else vendedoresFiltradosPorBusqueda


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
            if (vendedoresList.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.SentimentDissatisfied,
                            contentDescription = "No encontramos el vendedor que buscas",
                            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                            modifier = Modifier.size(160.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "¡No encotramos el vendedor que buscas!",
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
            items(vendedoresList) { vendedor ->
                VendedorCard(
                    vendedor = vendedor,
                    navController = navController,viewModel
                )
            }
        }
    }
}
