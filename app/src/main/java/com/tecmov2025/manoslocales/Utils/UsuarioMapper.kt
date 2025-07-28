package com.tecmov2025.manoslocales.Utils

import com.tecmov2025.manoslocales.Database.Entity.UsuarioEntity
import com.tecmov2025.manoslocales.Networking.UsuarioDTO

fun UsuarioDTO.toEntity(): UsuarioEntity {
    return UsuarioEntity(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        email = this.email,
        pass = this.pass
    )
}
