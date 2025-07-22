package com.tecmov2025.manoslocales.Utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductScreen(viewModel: ProductViewModel, navController: NavController) {
    var producto = viewModel.productoSeleccionado
    if (producto == null) {
        navController.popBackStack()
        return
    }
    else
    {
        BackHandler {
            viewModel.desapilarProducto()
        }
    }

    // Estado para el favorito
    val isFavorite by viewModel.productoEsFavorito(producto.producto.id).collectAsState(initial = false)


    Scaffold()
    {
        padding ->
        ProductScreenBody(producto,padding,isFavorite, viewModel, navController)
    }
}
@Composable
fun ProductScreenBody(
    producto: ProductoConVendedor,
    padding: PaddingValues,
    isFavorite: Boolean,
    viewModel: ProductViewModel,
    navController: NavController
)
{
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val productosMismoVendedor by viewModel.productosDeUnMismoVendedor(producto.vendedor.id).collectAsState()


    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ){
            Text(
                text = producto.producto.nombre,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 32.sp),
                modifier = Modifier.align(Alignment.Center)
            )
            //boton favorito
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp),
                onClick = {
                    if (isFavorite) {
                        viewModel.eliminarFavorito(producto.producto.id)
                    } else {
                        viewModel.añadirFavorito(producto.producto.id)
                    }
                }
            ){
                //si está favorito, muestra el ícono relleno
                val heartIcon = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
                Icon(
                    imageVector = heartIcon,
                    contentDescription = "Marcar como favorito",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Text(text = "Ubicación: ${producto.producto.ubicacion}", style = MaterialTheme.typography.bodyMedium)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ){
            items(producto.producto.images.size) { index ->
                AsyncImage(
                    model = producto.producto.images[index],
                    contentDescription = null,
                    modifier = Modifier
                        .height(400.dp)
                        .width(350.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }


        Text(text = "Precio: $${String.format("%.2f", producto.producto.precio)}", style = MaterialTheme.typography.titleMedium)

        CustomButton(
            onClick = {
                contactarVendedor(context, producto)
            },
            text = "Contactar al vendedor"
        )

        Text(text = "Descripción:", style = MaterialTheme.typography.titleMedium)
        Text(text = producto.producto.descripcion, style = MaterialTheme.typography.bodyLarge)

        Text(
            text = "Más productos de ${producto.vendedor.nombre}:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .align(Alignment.Start)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productosMismoVendedor.size) { index ->
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                            .height(220.dp)
                    ) {
                        ProductoCard(
                            producto = productosMismoVendedor[index],
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }

    }

}

fun contactarVendedor(context: Context, producto: ProductoConVendedor) {
    if (producto.producto.telefono.isNotEmpty()) {
        val uri = Uri.parse("https://wa.me/${producto.producto.telefono}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } else if (producto.producto.email.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${producto.producto.email}")
            putExtra(Intent.EXTRA_SUBJECT, "Consulta sobre ${producto.producto.nombre}")
            putExtra(Intent.EXTRA_TEXT, "Hola ${producto.vendedor}, me interesa tu producto.")
        }
        context.startActivity(intent)
    }
}
