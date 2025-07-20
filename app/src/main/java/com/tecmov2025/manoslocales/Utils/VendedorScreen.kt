package com.tecmov2025.manoslocales.Utils

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsNone

@Composable
fun VendedorScreen(
    vendedorNombre: String,
    viewModel: ProductViewModel,
    navController: NavController
) {
    val vendedorNombreDecoded = URLDecoder.decode(vendedorNombre, StandardCharsets.UTF_8.toString())
    val vendedor: Vendedor? = ExamplesVendedoresList().vendedoresList.find { it.nombre == vendedorNombreDecoded }
    val productosDelVendedor = viewModel.productos.value.filter { it.vendedor == vendedorNombreDecoded }

    if (vendedor == null) {
        Text("Vendedor no encontrado")
        return
    }

    val TAG = "VendedorScreen"

    viewModel.productos.value.forEach {
        Log.d(TAG, "Producto: ${it.nombre}, Vendedor: ${it.vendedor}")
    }
    Log.d(TAG, "Buscando productos del vendedor: $vendedorNombreDecoded")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 38.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto de perfil
                AsyncImage(
                    model = vendedor.perfilFoto,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = vendedor.nombre,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Botones Seguir y notificaciones
                var siguiendo by remember { mutableStateOf(false) }
                var notificacionesActivas by remember { mutableStateOf(false) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Button(
                        onClick = { siguiendo = !siguiendo },
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text(if (siguiendo) "Siguiendo" else "Seguir")
                    }

                    IconButton(
                        onClick = { notificacionesActivas = !notificacionesActivas }
                    ) {
                        Icon(
                            imageVector = if (notificacionesActivas) Icons.Default.Notifications else Icons.Default.NotificationsNone,
                            contentDescription = "Notificaciones",
                            tint = if (notificacionesActivas) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Text(
                text = vendedor.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Productos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Agrupar productos de a 2
        val productosAgrupados = productosDelVendedor.chunked(2)

        items(productosAgrupados) { grupo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp)
                ) {
                    ProductoCard(grupo[0], viewModel, navController)
                }

                if (grupo.size > 1) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(200.dp)
                    ) {
                        ProductoCard(grupo[1], viewModel, navController)
                    }
                } else {
                    // Si solo hay un producto en la fila, llenar el espacio vacío para mantener el layout consistente
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

    }
}
