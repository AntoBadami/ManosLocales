package com.tecmov2025.manoslocales.ActivityHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecmov2025.manoslocales.Utils.CustomScaffold
import com.tecmov2025.manoslocales.Utils.Opcion
import com.tecmov2025.manoslocales.Utils.OptionCard
import com.tecmov2025.manoslocales.Utils.OptionDropdownCard
import com.tecmov2025.manoslocales.Utils.OptionSwitchCard
import com.tecmov2025.manoslocales.Utils.TipoOpcion

/**
 * Pantalla de configuraciones
 */
@Composable
fun ConfigScreen()
{ CustomScaffold("Configuracion"){ padding -> ConfiguracionesBody(padding)} }

/**
 * Cuerpo de la interfaz de configuraciones
 */
@Composable
fun ConfiguracionesBody(padding: PaddingValues)
{
    val switchesState = remember { mutableStateListOf(true) }
    val opciones = listOf(
        Opcion("Registrar historial de busqueda", tipo = TipoOpcion.SWITCH),
        Opcion("Tiempo de notificaciones")
    )

    val opcionesNotificacion = listOf("6 horas", "1 día", "2 días", "1 sem", "Nunca")
    val seleccionNotificacion = remember { mutableStateOf(opcionesNotificacion[1]) }

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
                            else -> OptionDropdownCard(
                                opcion = opciones[i],
                                opcionesLista = opcionesNotificacion,
                                seleccion = seleccionNotificacion.value,
                                onSeleccion = { seleccionNotificacion.value = it }
                            )
                        }

                }
            }
        }
    }
}


