package com.tecmov2025.manoslocales.Utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext

@Composable
fun VendedorScreen(
    viewModel: ProductViewModel,
    navController: NavController
) {
    val vendedor = viewModel.vendedorSeleccionado.value
    val context = LocalContext.current
    if(vendedor == null)
        return
    val productosDelVendedor = viewModel.productosDeUnMismoVendedor(vendedor.id).collectAsState()

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
                val siguiendo by viewModel.vendedorEstaEnSeguidos(vendedor.id).collectAsState()


                val notificacionesActivas by viewModel.notificacionesDeVendedorActivadas(vendedor.id).collectAsState()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Button(
                        onClick = {
                            if(siguiendo)
                            { viewModel.dejarDeSeguirVendedor(vendedor) }
                            else
                            {viewModel.seguirVendedor(vendedor)}

                                  },
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text(if (siguiendo) "Siguiendo" else "Seguir")
                    }

                    IconButton(
                        enabled = siguiendo,
                        onClick = {
                            if(notificacionesActivas)
                            { viewModel.desactivarNotificaciones(vendedor,context)}
                            else
                            {
                                viewModel.activarNotificaciones(vendedor,
                                    "¡"+ vendedor.nombre + " y más vendedores te esperan!",
                                    "¿Qué esperas para darte una vuelta por la App?",context = context)}
                        }
                    ) {
                        Icon(
                            imageVector = if (notificacionesActivas) Icons.Default.Notifications else Icons.Default.AddAlert,
                            contentDescription = "Notificaciones",
                            tint = if (!siguiendo) {
                                // Grisado si no lo seguís
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                            } else if (notificacionesActivas) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
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
        val productosAgrupados = productosDelVendedor.value.chunked(2)

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
