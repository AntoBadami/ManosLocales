package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsuarioEntity(
    @PrimaryKey
    val id : Int,
    val nombre: String,
    val apellido: String,
    val email: String,
    val pass: String
)