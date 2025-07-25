package com.tecmov2025.manoslocales.SharedPreferences

import android.content.Context
sealed class ConfigNames(val config: String)
{
    object HistorialBusquedaConfig : ConfigNames("HistorialBusqueda")
    object TiempoDeNotificacionesConfig: ConfigNames("TiempoNotificacion")
    object SesionStateConfig : ConfigNames("SesionState")
}

enum class CONFIG_TIEMPO(val descripcion: String)
{
    H6("6 horas"),
    D1("1 día"),
    D2("2 días"),
    S1("1 semana"),
    NUNCA("Nunca");

    companion object {

        private val byDescripcion = values().associateBy { it.descripcion }

        /** Devuelve el enum cuyo `descripcion` coincide, o `NUNCA` (o nulo) si no existe */

        fun fromDescripcion(desc: String): CONFIG_TIEMPO =
            byDescripcion[desc] ?: NUNCA
    }
}

class ConfigPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("ConfigPref", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    init {
        // Establecer valores por defecto solo si no existen
        if (!sharedPreferences.contains(ConfigNames.HistorialBusquedaConfig.config)) {
            editor.putBoolean(ConfigNames.HistorialBusquedaConfig.config, true)
        }
        if (!sharedPreferences.contains(ConfigNames.TiempoDeNotificacionesConfig.config)) {
            editor.putString(ConfigNames.TiempoDeNotificacionesConfig.config, CONFIG_TIEMPO.H6.name)
        }

        editor.apply()
    }


    fun activarHistorialDeBusqueda()
    {
        editor.putBoolean(ConfigNames.HistorialBusquedaConfig.config,true)
        editor.apply()
    }

    fun desactivarHistorialDeBusqueda()
    {
        editor.putBoolean(ConfigNames.HistorialBusquedaConfig.config,false)
        editor.apply()
    }

    fun setTiempoNotificaciones(tiempo : CONFIG_TIEMPO)
    {
        editor.putString(ConfigNames.TiempoDeNotificacionesConfig.config,tiempo.name)
        editor.apply()
    }

    fun getTiempoNotificacionesConfig(): CONFIG_TIEMPO
    {
        return CONFIG_TIEMPO.valueOf(sharedPreferences.getString(ConfigNames.TiempoDeNotificacionesConfig.config,
            CONFIG_TIEMPO.NUNCA.name)?: CONFIG_TIEMPO.NUNCA.name)
    }

    /** Retorna el estado de una configuracion booleana
     * @param nombreConfiguracionBooleana de la configuracion
     * @return estado de la configuracion, false si no existe*/
    fun getEstadoDeConfiguracionBoolean(nombreConfiguracionBoolean : String): Boolean
    {
        return sharedPreferences.getBoolean(nombreConfiguracionBoolean,false)
    }

    fun setLoggedIn() {
            editor.putBoolean(ConfigNames.SesionStateConfig.config, true)
            editor.apply()
    }


    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(ConfigNames.SesionStateConfig.config, false)
    }

    fun clearSession() {
        editor.putBoolean(ConfigNames.SesionStateConfig.config, false)
            .apply()
    }
}