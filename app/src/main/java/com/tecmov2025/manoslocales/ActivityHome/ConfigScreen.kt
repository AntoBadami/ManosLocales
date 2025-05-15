package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecmov2025.manoslocales.Utils.Opcion
import com.tecmov2025.manoslocales.Utils.OptionCard
import com.tecmov2025.manoslocales.Utils.OptionSwitchCard
import com.tecmov2025.manoslocales.Utils.TipoOpcion

// Scaffold de interfaz de configuraciones
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen()
{
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Configuracion",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 15.dp)) },
                navigationIcon = {},
                actions = {}
            )
        }
    ){ padding -> ConfiguracionesBody(padding)}


}

// Cuerpo de la interfaz de configuraciones
@Composable
fun ConfiguracionesBody(padding: PaddingValues)
{
    val switchesState = remember { mutableStateListOf(true) }
    val opciones = listOf(
        Opcion("Registrar historial de busqueda", tipo = TipoOpcion.SWITCH),
        Opcion("Tiempo de notificaciones")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxSize()
            ){
                items(opciones.size) {
                    i ->
                        when (opciones[i].tipo) {
                            TipoOpcion.SWITCH -> OptionSwitchCard(opciones[i], switchesState, i)
                            else -> OptionCard(opciones[i])
                        }

                }
            }
        }
    }
}


