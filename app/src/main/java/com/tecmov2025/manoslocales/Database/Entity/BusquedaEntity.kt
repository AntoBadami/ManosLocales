package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busquedas")
data class BusquedaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val termino: String
)