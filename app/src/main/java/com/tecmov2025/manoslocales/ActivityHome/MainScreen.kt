package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.tecmov2025.manoslocales.Utils.Producto
import com.tecmov2025.manoslocales.Utils.ProductoCard

// Cuerpo de interfaz principal
@Composable
fun MainScreen(paddingBarraDeBusqueda: PaddingValues, viewModel: ProductViewModel, navController: NavController)
{
    val productos = ExampleProductList().productosList

    //productos en pares
    val productosAgrupados = productos.chunked(2)

    Column( modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 3.dp)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(productosAgrupados){
            grupo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingBarraDeBusqueda),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) //espacio entre productos
                ){
                    val modifier = Modifier
                        .weight(1f)
                        .height(200.dp)
                    //primer producto
                    ProductBox(modifier,producto = grupo[0], viewModel, navController)
                    //segundo producto
                    if (grupo.size > 1) {
                        ProductBox(modifier,producto = grupo[1], viewModel, navController)
                    }
                }
            }
        }
    }
}
@Composable
fun ProductBox(modifier: Modifier,producto: Producto, viewModel: ProductViewModel,navController: NavController)
{
    Box(modifier){
        ProductoCard(producto, viewModel, navController)
    }
}