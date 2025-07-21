package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendedores")
data class VendedorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val perfilFoto: String,
    val email: String,
    val telefono: String,
    val ubicacion: String
)