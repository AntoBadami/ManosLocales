package com.tecmov2025.manoslocales.ActivityFavoritos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.Utils.BarraDeBusqueda
import com.tecmov2025.manoslocales.Utils.ExampleProductList
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.ProductoCard

/**
 * Pantalla de favoritos
 * @param padding dado por la barra de busqueda
 * @param viewmodel necesario para seleccionar productos
 */
@Composable
fun FavoritosScreen(navController: NavController,viewModel: ProductViewModel) {

    BarraDeBusqueda(navController,viewModel){ padding,viewModel,navController ->
        FavoritosScreenBody(padding,viewModel,navController) }


}
/**
 * Cuerpo de favoritos
 * @param padding dado por la barra de busqueda
 * @param viewmodel necesario para seleccionar productos
 * @param navController necesario para navegacion
 */
@Composable
fun FavoritosScreenBody(padding: PaddingValues, viewModel: ProductViewModel, navController: NavController)
{
    //productos ejemplo
    val productos = ExampleProductList().productosList
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding),
    ){
        Column(
            modifier = Modifier
                .padding(top = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Productos Favoritos",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 32.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ){
                items(productos.size) {
                    index ->
                        if(productos[index].favoritoState)
                        { ProductoCard(productos[index], viewModel, navController, true) }}
            }
        }
    }

}
