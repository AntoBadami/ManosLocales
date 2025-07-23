package com.tecmov2025.manoslocales.SharedPreferences

import android.content.Context

class ConfigPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("ConfigPref", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


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

    /** Retorna el estado de una configuracion booleana
     * @param nombreConfiguracionBooleana de la configuracion
     * @return estado de la configuracion, false si no existe*/
    fun getEstadoDeConfiguracionBoolean(nombreConfiguracionBoolean : String): Boolean
    {
        return sharedPreferences.getBoolean(nombreConfiguracionBoolean,false)
    }
}