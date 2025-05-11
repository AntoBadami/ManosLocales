package com.tecmov2025.manoslocales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(paddingBarraDeBusqueda: PaddingValues) {
    //lista de productos
    val productosMock = List(30) { index ->
        Producto(
            nombre = "Producto $index",
            descripcion = "Descripción del producto $index",
            precio = index * 3.0
        )
    }
    //productos en pares
    val productosAgrupados = productosMock.chunked(2)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
                    ProductoCard(producto = grupo[0])
                }
                //segundo producto
                if (grupo.size > 1) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductoCard(producto = grupo[1])
                    }
                }
            }
        }
    }

}
@Composable
fun ProductoCard(producto: Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            //espacio para la imagen del producto
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.DarkGray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = producto.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}