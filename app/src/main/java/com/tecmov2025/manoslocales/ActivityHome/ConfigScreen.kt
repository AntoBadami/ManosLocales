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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tecmov2025.manoslocales.SharedPreferences.CONFIG_TIEMPO
import com.tecmov2025.manoslocales.SharedPreferences.ConfigNames
import com.tecmov2025.manoslocales.SharedPreferences.ConfigPreferences
import com.tecmov2025.manoslocales.Utils.ConfigDropdownCard
import com.tecmov2025.manoslocales.Utils.ConfigSwitchCard
import com.tecmov2025.manoslocales.Utils.CustomScaffold
import com.tecmov2025.manoslocales.Utils.ProductViewModel

/**
 * Pantalla de configuraciones
 */
@Composable
fun ConfigScreen(viewModel: ProductViewModel)
{ CustomScaffold("Configuracion"){ padding -> ConfiguracionesBody(padding,viewModel)} }

/**
 * Cuerpo de la interfaz de configuraciones
 */
@Composable
fun ConfiguracionesBody(padding: PaddingValues, viewmodel: ProductViewModel)
{
    val context = LocalContext.current
    val config = ConfigPreferences(LocalContext.current)

    // Registrar historial de busqueda
    var registrarHistorialChecked by remember {
        mutableStateOf(
            config.getEstadoDeConfiguracionBoolean(ConfigNames.HistorialBusquedaConfig.config)) }

    fun historialBusquedaOnCheckedChange(new : Boolean)
    {
        registrarHistorialChecked = new
        if(new)
        { config.activarHistorialDeBusqueda() }
        else
        { config.desactivarHistorialDeBusqueda() }

    }

    // Tiempo de notificaciones
    viewmodel.obtenerTiempoNotificaciones(context)
    val seleccionNotificacion by viewmodel.tiempoNotificaciones.collectAsState()

    val opcionesNotificacion = CONFIG_TIEMPO.values().map { it.descripcion }

    fun tiempoDeNotificacionesOnSeleccion(new: String)
    { viewmodel.establecerTiempoNotificaciones(context,CONFIG_TIEMPO.fromDescripcion(new)) }

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
                item {
                    ConfigSwitchCard("Registrar historial de busqueda",
                        checked = registrarHistorialChecked,
                        onCheckedChange = ::historialBusquedaOnCheckedChange)
                }
                item{
                    ConfigDropdownCard(
                        text = "Tiempo de notificaciones",
                        opcionesLista = opcionesNotificacion,
                        seleccion = seleccionNotificacion.descripcion,
                        onSeleccion = ::tiempoDeNotificacionesOnSeleccion)

                }
            }
        }
    }
}
