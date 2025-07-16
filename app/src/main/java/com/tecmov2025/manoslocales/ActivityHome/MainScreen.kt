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
//noinspection UsingMaterialAndMaterial3Libraries
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
import com.tecmov2025.manoslocales.Utils.BarraDeBusqueda
import com.tecmov2025.manoslocales.Utils.ExampleProductList
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.ProductoCard
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.LocalTextStyle
import androidx.compose.ui.Alignment

/**
 * Cuerpo de interfaz principal
 * @param paddingBarraDeBusqueda Dado por el scaffold que la contiene
 * @param viewModel utilizado para seleccionar un producto
 * @param navController utilizado para redirigir al ProductScreen
 */
@Composable
fun MainScreen(viewModel: ProductViewModel, navController: NavController)
{
    BarraDeBusqueda(viewModel = viewModel, navController = navController)
    { padding, viewModel, navController ->
        MainScreenBody(padding, viewModel, navController)
    }
}
@Composable
fun MainScreenBody(paddingBarraDeBusqueda: PaddingValues, viewModel: ProductViewModel, navController: NavController)
{
    val productos = viewModel.productos.value

    val vendedores = remember(productos) {
        buildList {
            add("Todos")
            addAll(productos.map { it.vendedor }.distinct())
        }
    }
    var vendedorSeleccionado by rememberSaveable { mutableStateOf("Todos") }

    val categorias = remember(productos)
    {
        buildList {
            add("Todas")
            addAll(productos.map { it.categoria }.distinct())
        }
    }
    var categoriaSeleccionada by rememberSaveable { mutableStateOf("Todas") }

    val productosFiltrados = remember(productos, categoriaSeleccionada, vendedorSeleccionado)
    {
        productos.filter {
            (categoriaSeleccionada == "Todas" || it.categoria == categoriaSeleccionada) &&
                    (vendedorSeleccionado == "Todos" || it.vendedor == vendedorSeleccionado)
        }
    }

    //productos en pares
    val productosAgrupados = productosFiltrados.chunked(2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = paddingBarraDeBusqueda.calculateTopPadding())
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoriaFilter(
                categorias = categorias,
                categoriaSeleccionada = categoriaSeleccionada,
                onCategoriaSeleccionada = { categoriaSeleccionada = it },
                modifier = Modifier.weight(1f)
            )
            VendedorFilter(
                vendedores = vendedores,
                vendedorSeleccionado = vendedorSeleccionado,
                onVendedorSeleccionado = { vendedorSeleccionado = it },
                modifier = Modifier.weight(1f)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(productosAgrupados){ grupo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) //espacio entre productos
                ){
                    //primer producto
                    Box(modifier = Modifier
                            .weight(1f)
                            .height(200.dp)
                    ){
                        ProductoCard(grupo[0], viewModel, navController)
                    }
                    //segundo producto
                    if (grupo.size > 1)
                        Box(modifier = Modifier
                            .weight(1f)
                            .height(200.dp)
                        ){
                            ProductoCard(grupo[1], viewModel, navController)
                        }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendedorFilter(
    vendedores: List<String>,
    vendedorSeleccionado: String,
    onVendedorSeleccionado: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = vendedorSeleccionado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Vendedor") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            vendedores.forEach { vendedor ->
                DropdownMenuItem(
                    text = { Text(vendedor) },
                    onClick = {
                        onVendedorSeleccionado(vendedor)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaFilter(
    categorias: List<String>,
    categoriaSeleccionada: String,
    onCategoriaSeleccionada: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            value = categoriaSeleccionada,
            onValueChange = {},
            readOnly = true,
            label = { Text("Categoría") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
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
