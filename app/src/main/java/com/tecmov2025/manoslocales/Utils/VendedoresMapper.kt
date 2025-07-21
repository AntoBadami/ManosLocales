package com.tecmov2025.manoslocales.Utils

import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import com.tecmov2025.manoslocales.Networking.VendedorDTO


fun VendedorDTO.toEntity(): VendedorEntity {
    return VendedorEntity(
        nombre = this.nombre,
        descripcion = this.descripcion,
        ubicacion = this.ubicacion,
        perfilFoto = this.perfilFoto,
        email = this.email,
        telefono = this.telefono,
    )
}

fun List<VendedorDTO>.toEntityList(): List<VendedorEntity> {
    return this.map { it.toEntity() }
}