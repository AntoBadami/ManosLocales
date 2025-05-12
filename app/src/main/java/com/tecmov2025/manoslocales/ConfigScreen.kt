package com.tecmov2025.manoslocales

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConfigScreen(navController: NavController)
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            CustomTitleText("Configuraciones")

            LazyColumn(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxSize()
            )
            {
                items(opciones.size) { i ->
                    when (opciones[i].tipo) {
                        TipoOpcion.SWITCH -> OptionSwitchCard(opciones[i], switchesState, i)
                        else -> OptionCard(opciones[i])
                    }

                }
            }
        }
    }

}


