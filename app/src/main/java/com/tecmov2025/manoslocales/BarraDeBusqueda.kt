package com.tecmov2025.manoslocales
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomScaffold()
{
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent ={ CustomDrawer()},
        topBar = { CustomTopAppBar(coroutineScope,scaffoldState,context)},
        content = { paddingValues -> MainScreen(paddingValues)}
    )
}

@Composable
fun CustomTopAppBar(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState, context : Context)
{
    var textoBusqueda by remember { mutableStateOf("") }
    Column {
        // Agregar un espacio para la barra de estado
        Spacer(modifier = Modifier.height(24.dp))
        TopAppBar(
            //Buscador
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
            }
        )
    }
}
@Composable
fun CustomDrawer()
{
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Opción 1")
        Text("Opción 2")
    }


}


fun FavotitosIconButtonAction(context: Context)
{
    val intent = Intent(context, FavoritosActivity::class.java)
    context.startActivity(intent)
}