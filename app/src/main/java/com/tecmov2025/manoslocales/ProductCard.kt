package com.tecmov2025.manoslocales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ProductoCard(producto: Producto, viewModel: ProductViewModel, navController: NavController, isFavoritoView: Boolean = false) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            viewModel.seleccionarProducto(producto)
            navController.navigate("ProductoScreen")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val imageSize = if (isFavoritoView) 150.dp else 64.dp
            val spacerSize = if (isFavoritoView) 20.dp else 8.dp
            val titleStyle = if (isFavoritoView) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium
            val bodyStyle = if (isFavoritoView) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium

            //espacio para la imagen del producto
            Box(
                modifier = Modifier
                    .size(imageSize)
                    .background(Color.DarkGray)
            )

            Spacer(modifier = Modifier.height(spacerSize))

            Text(
                text = producto.nombre,
                style = titleStyle,
                color = Color.DarkGray
            )
            Text(
                text = producto.descripcion,
                style = bodyStyle,
                color = Color.DarkGray
            )
        }
    }
}
