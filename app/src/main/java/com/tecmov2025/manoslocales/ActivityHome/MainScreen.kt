package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.Utils.ExampleProductList
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Producto
import com.tecmov2025.manoslocales.Utils.ProductoCard

/**
 * Cuerpo de interfaz principal
 * @param paddingBarraDeBusqueda Dado por el scaffold que la contiene
 * @param viewModel utilizado para seleccionar un producto
 * @param navController utilizado para redirigir al ProductScreen
 */
@Composable
fun MainScreen(paddingBarraDeBusqueda: PaddingValues, viewModel: ProductViewModel, navController: NavController)
{
    val productos = ExampleProductList().productosList

    val categorias = remember(productos)
    {
        buildList {
            add("Todas")
            addAll(productos.map { it.categoria }.distinct())
        }
    }
    var categoriaSeleccionada by rememberSaveable { mutableStateOf("Todas") }

    val productosFiltrados = remember(productos, categoriaSeleccionada)
    {
        if (categoriaSeleccionada == "Todas") productos
        else productos.filter { it.categoria == categoriaSeleccionada }
    }

    //productos en pares
    val productosAgrupados = productosFiltrados.chunked(2)

    Column( modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 3.dp)
    ){
        CategoriaFilter(
            categorias = categorias,
            categoriaSeleccionada = categoriaSeleccionada,
            onCategoriaSeleccionada = { categoriaSeleccionada = it }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(productosAgrupados){ grupo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingBarraDeBusqueda),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) //espacio entre productos
                ){
                    //primer producto
                    Box(modifier = Modifier
                            .weight(1f)
                            .height(200.dp)){ ProductoCard(grupo[0], viewModel, navController) }
                    //segundo producto
                    if (grupo.size > 1)
                        Box(modifier = Modifier
                            .weight(1f)
                            .height(200.dp)){ ProductoCard(grupo[1], viewModel, navController) }
                }
            }
        }
    }
}

@Composable
fun CategoriaFilter(
    categorias: List<String>,
    categoriaSeleccionada: String,
    onCategoriaSeleccionada: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        TextField(
            value = categoriaSeleccionada,
            onValueChange = {},
            readOnly = true,
            label = { Text("Categoría") },
            trailingIcon = {
                IconButton (onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Abrir categorías"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categorias.forEach { categoria ->
                DropdownMenuItem(
                    text = { Text(categoria) },
                    onClick = {
                        onCategoriaSeleccionada(categoria)
                        expanded = false
                    }
                )
            }
        }
    }
}

