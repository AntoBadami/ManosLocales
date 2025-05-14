package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.Utils.ExampleProductList
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.ProductoCard

@Composable
fun MainScreen(paddingBarraDeBusqueda: PaddingValues, viewModel: ProductViewModel, navController: NavController) {
    val productos = ExampleProductList().productosList
    //productos en pares
    val productosAgrupados = productos.chunked(2)
Column( modifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background))
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(productosAgrupados) { grupo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingBarraDeBusqueda),
                horizontalArrangement = Arrangement.spacedBy(8.dp) //espacio entre productos
            ) {
                //primer producto
                Box(modifier = Modifier.weight(1f)) {
                    ProductoCard(producto = grupo[0], viewModel, navController)
                }
                //segundo producto
                if (grupo.size > 1) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductoCard(producto = grupo[1], viewModel, navController)
                    }
                }
            }
        }
    }
}


}