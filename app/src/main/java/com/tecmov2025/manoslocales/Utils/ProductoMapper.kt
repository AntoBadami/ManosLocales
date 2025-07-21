package com.tecmov2025.manoslocales.Utils

import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import com.tecmov2025.manoslocales.Networking.ProductoDTO

fun ProductoDTO.toEntity(): ProductoEntity {
    return ProductoEntity(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion,
        precio = this.precio,
        categoria = this.categoria,
        ubicacion = this.ubicacion,
        images = this.images,
        vendedor = this.vendedor,
        email = this.email,
        telefono = this.telefono,
        //TODO Eliminar esto
        favoritoState = this.favoritoState
    )
}

fun List<ProductoDTO>.toEntityList(): List<ProductoEntity> {
    return this.map { it.toEntity() }
}