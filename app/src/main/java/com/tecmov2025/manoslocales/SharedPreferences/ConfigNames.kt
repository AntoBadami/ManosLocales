package com.tecmov2025.manoslocales.SharedPreferences

sealed class ConfigNames(val config: String)
{
    object HistorialBusquedaConfig : ConfigNames("HistorialBusqueda")
}