package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.TypeConverter
/**
 * Clase encargada de convertir datos complejos para almacenar en la Base de datos
 */
class Converters {
    @TypeConverter
    fun fromListToString(value: List<String>): String {
        return value.joinToString(separator = "|")
    }

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        return if (value.isEmpty()) emptyList() else value.split("|")
    }
}
