package com.tecmov2025.manoslocales.Database.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "seguidos",
    foreignKeys = [
        ForeignKey(
            entity = VendedorEntity::class,
            parentColumns = ["id"],
            childColumns = ["vendedor"],
            onDelete = ForeignKey.CASCADE          // comportamiento al borrar el padre
        )],
    indices = [Index("vendedor")]           // mejora el rendimiento en joins
)
data class SeguidoEntity(
    @PrimaryKey
    val vendedor: Int,
    val notificacion: Boolean
)