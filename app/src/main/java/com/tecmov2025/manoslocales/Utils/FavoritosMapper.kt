package com.tecmov2025.manoslocales.Utils

import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Networking.FavoritoDTO

fun FavoritoDTO.toEntity(): FavoritoEntity {
    return FavoritoEntity(
        id = this.id,
        producto = this.producto
    )
}

fun List<FavoritoDTO>.toEntityList(): List<FavoritoEntity> {
    return this.map { it.toEntity() }
}