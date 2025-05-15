package com.tecmov2025.manoslocales.ActivityHome
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.ActivityLogin.LoginActivity
import com.tecmov2025.manoslocales.ActivityFavoritos.FavoritosActivity
import com.tecmov2025.manoslocales.Utils.Opcion
import com.tecmov2025.manoslocales.Utils.OptionCard
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Barra de busqueda
 * @param navController permite viajar entre las pantallas
 * @param viewModel necesario para la seleccion de productos
 */
@Composable
fun BarraDeBusqueda(navController: NavController, viewModel: ProductViewModel)
{
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent ={ CustomDrawer(navController,context)},
        topBar = { CustomTopAppBar(coroutineScope,scaffoldState,context)},
        content = { paddingValues -> MainScreen(paddingValues,viewModel = viewModel, navController = navController)}
    )
}

/**
 * TopAppBar que contiene la barra de busqueda, el boton para
 * acceder al Drawer lateral y el boton para acceder a favoritos
 */
@Composable
fun CustomTopAppBar(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState, context : Context)
{
    var textoBusqueda by remember { mutableStateOf("") }

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            modifier = Modifier
                .padding(WindowInsets.statusBars.asPaddingValues())
                .fillMaxWidth()
                .height(80.dp),
            title = {
                TextField(
                    modifier =Modifier
                        .heightIn(min = 48.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(50.dp)),
                    value = textoBusqueda,
                    onValueChange = { textoBusqueda = it },
                    placeholder = { Text("Buscar...") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.Gray
                        )},
                    colors = TextFieldDefaults
                        .textFieldColors(textColor = Color.DarkGray,
                                        cursorColor = MaterialTheme.colorScheme.primary,
                                        backgroundColor = Color.Transparent,
                                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                        unfocusedIndicatorColor = Color.Transparent
                        )
                    )
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = { coroutineScope.launch { scaffoldState.drawerState.open()} }
                ) {Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.background)}
            },
            actions = {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = { FavotitosIconButtonAction(context = context) }
                ) {
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favoritos",
                        tint = MaterialTheme.colorScheme.background) }
            },
            backgroundColor = MaterialTheme.colorScheme.primary
        )
    }
}

/**
 * Barra lateral Drawer personalizada
 * @param navController permite navegar a las pantallas de las respectivas opciones
 * @param context permite iniciar activity Login por opcion "Cerrar Sesion"
 */
@Composable
fun CustomDrawer(navController: NavController,context: Context)
{
    val opciones = listOf<Opcion>(
        Opcion("Mi perfil", { navController.navigate("PerfilScreen") }, icon = Icons.Default.Person),
        Opcion("Configuracion", { navController.navigate("ConfigScreen") }, icon = Icons.Default.Settings),
        Opcion("Enviar mail al desarrollador", {  }, icon = Icons.Default.Email),
        Opcion("Cerrar Sesion", {context.startActivity(Intent(context, LoginActivity::class.java)) }, icon = Icons.Default.Close)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ){
            items(opciones.size) {
                i ->
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .padding(top = 5.dp)
                ){ OptionCard(opciones[i]) }
            }
        }

    }
}


/**
 * Accion de boton de favoritos
 * Utiliza intent para navegar a Activity Favoritos
 * @param context para hacer la navegacion
 */
fun FavotitosIconButtonAction(context: Context)
{
    val intent = Intent(context, FavoritosActivity::class.java)
    context.startActivity(intent)
}