package com.tecmov2025.manoslocales.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProductScreen(viewModel: ProductViewModel)
{
    var producto = viewModel.productoSeleccionado!!
    // Estado para el favorito
    var isFavorite = remember { mutableStateOf(false) }
    isFavorite.value = producto.favoritoState

    Scaffold()
    {
        padding ->
        ProductScreenBody(producto,padding,isFavorite)
    }
}
@Composable
fun ProductScreenBody(producto: Producto, padding: PaddingValues, isFavorite: MutableState<Boolean>)
{
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 32.sp)
            )
            //boton favorito
            IconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = {
                    isFavorite.value = !isFavorite.value
                    producto.favoritoState = isFavorite.value }
            ){
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
        ){
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
        ){
            Text(text = "Cantidad:", style = MaterialTheme.typography.titleMedium)
            // disminuir
            IconButton(
                onClick = { if (cantidad > 1) cantidad-- }
            ){ Text("-", style = MaterialTheme.typography.headlineMedium) }

            Text(text = cantidad.toString(), style = MaterialTheme.typography.titleMedium)
            // aumentar
            IconButton(
                onClick = { cantidad++ }
            ){ Text("+", style = MaterialTheme.typography.headlineMedium) }
        }
        CustomButton(onClick = { }, text = "Comprar")
        Text(text = "Descripción:", style = MaterialTheme.typography.titleMedium)
        Text(text = producto.descripcion, style = MaterialTheme.typography.bodyLarge)
        //contactos
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            IconButton(onClick = { }) { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
            IconButton(onClick = { }) { Icon(imageVector = Icons.Default.Phone, contentDescription = "Teléfono") }
            IconButton(onClick = { }) { Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir") }
        }

    }

}



