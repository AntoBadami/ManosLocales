package com.tecmov2025.manoslocales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(viewModel: ProductViewModel)
{
    var producto = viewModel.productoSeleccionado!!
    // Estado para el favorito
    var isFavorite = remember { mutableStateOf(false) }
    isFavorite.value = producto.favoritoState


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 32.sp
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
                //boton favorito
                IconButton(
                    onClick = {
                        isFavorite.value = !isFavorite.value
                        producto.favoritoState = isFavorite.value },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(48.dp)
                ) {
                    //si está favorito, muestra el ícono relleno
                    val heartIcon = if (isFavorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
                    Icon(
                        imageVector = heartIcon,
                        contentDescription = "Marcar como favorito",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(text = "Ubicación: ${producto.ubicacion}", style = MaterialTheme.typography.bodyMedium)

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                items(producto.images.size) { index ->
                    AsyncImage(
                        model = producto.images[index],
                        contentDescription = null,
                        modifier = Modifier
                            .height(400.dp)
                            .width(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }


            Text(text = "Precio: $${String.format("%.2f", producto.precio)}", style = MaterialTheme.typography.titleMedium)

            // estado de la cantidad seleccionada
            var cantidad by remember { mutableStateOf(1) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Cantidad:", style = MaterialTheme.typography.titleMedium)

                // disminuir
                IconButton(
                    onClick = {
                        if (cantidad > 1) cantidad--
                    }
                ) {
                    Text("-", style = MaterialTheme.typography.headlineMedium)
                }

                Text(text = cantidad.toString(), style = MaterialTheme.typography.titleMedium)

                // aumentar
                IconButton(
                    onClick = {
                        cantidad++
                    }
                ) {
                    Text("+", style = MaterialTheme.typography.headlineMedium)
                }
            }

            CustomButton(
                onClick = { },
                text = "Comprar"
            )

            Text(text = "Descripción:", style = MaterialTheme.typography.titleMedium)
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyLarge)

            //contactos
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Phone, contentDescription = "Teléfono")
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir")
                    }
            }

        }

    }
}

@Composable
fun ProductScreenPreview() {
    val producto = Producto(
        nombre = "Mate Imperial",
        descripcion = "Mate imperial artesanal de calabaza. Forrado en cuero con virola de acero inoxidable. Incluye bombilla de acero",
        precio = 22000.0,
        ubicacion = "Córdoba, Argentina",
        images = listOf(
            "https://acdn-us.mitiendanube.com/stores/001/640/893/products/sorteo-ms-5-70852b318a3a38277f17279601997418-640-0.webp",
            "https://acdn-us.mitiendanube.com/stores/001/640/893/products/9c663a0f-7169-4ce0-9079-84a0dc7594d21-07f82bdece7591b8ed16753829200230-640-0.webp"
        )
    )

}
