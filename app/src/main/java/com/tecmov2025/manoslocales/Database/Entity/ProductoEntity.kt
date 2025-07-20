package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val ubicacion: String,
    val images: List<String>,
    val categoria: String,
    val vendedor: String,
    val email: String,
    val telefono: String
)