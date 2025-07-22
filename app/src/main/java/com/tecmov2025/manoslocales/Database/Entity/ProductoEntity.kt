package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "productos",
    foreignKeys = [
    ForeignKey(
        entity = VendedorEntity::class,
        parentColumns = ["id"],
        childColumns = ["vendedor"],
        onDelete = ForeignKey.CASCADE          // comportamiento al borrar el padre
    )],
    indices = [Index("vendedor")]           // mejora el rendimiento en joins
    )
data class ProductoEntity(
    @PrimaryKey
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val ubicacion: String,
    val images: List<String>,
    val categoria: String,
    val vendedor: Int,
    val email: String,
    val telefono: String,
    val favoritoState: Boolean
)