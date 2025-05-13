package com.tecmov2025.manoslocales
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomScaffold(navController: NavController,viewModel: ProductViewModel)
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

@Composable
fun CustomTopAppBar(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState, context : Context)
{
    var textoBusqueda by remember { mutableStateOf("") }
    Column {
        TopAppBar(
            modifier = Modifier
                .padding(WindowInsets.statusBars.asPaddingValues()),
            title = {
                TextField(
                    value = textoBusqueda,
                    onValueChange = { textoBusqueda = it },
                    placeholder = { Text("Buscar...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.Red),
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                ) {}
            },
            actions = {
                IconButton(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.Yellow),
                    onClick = { FavotitosIconButtonAction(context = context) }
                ) { }
            })
    }
}


@Composable
fun CustomDrawer(navController: NavController,context: Context)
{
    val opciones = listOf<Opcion>(
            Opcion("Mi perfil",{navController.navigate("PerfilScreen")}),
            Opcion("Configuracion",{navController.navigate("ConfigScreen")}),
            Opcion("Cerrar Sesion",{ context.startActivity(Intent(context, LoginActivity::class.java))}))

    LazyColumn(
        modifier = Modifier
        .padding(top = 40.dp)
        .fillMaxSize())
        {
            items(opciones.size) { i ->
                OptionCard(opciones[i])
            }
        }

        }




fun FavotitosIconButtonAction(context: Context)
{
    val intent = Intent(context, FavoritosActivity::class.java)
    context.startActivity(intent)
}