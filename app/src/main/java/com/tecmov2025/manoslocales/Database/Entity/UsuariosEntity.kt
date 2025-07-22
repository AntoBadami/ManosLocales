package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidad que mantiene los favoritos del usuario
 */
@Entity(tableName = "favoritos",
    foreignKeys = [
        ForeignKey(
            entity = ProductoEntity::class,
            parentColumns = ["id"],
            childColumns = ["producto"],
            onDelete = ForeignKey.CASCADE          // comportamiento al borrar el padre
        )],
    indices = [Index("producto")]           // mejora el rendimiento en joins
)
data class FavoritosEntity(
    @PrimaryKey
    val id: Int,
    val producto: Int
)