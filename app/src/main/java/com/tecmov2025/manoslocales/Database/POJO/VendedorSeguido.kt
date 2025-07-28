package com.tecmov2025.manoslocales.Database.POJO

import androidx.room.Embedded
import androidx.room.Relation
import com.tecmov2025.manoslocales.Database.Entity.SeguidoEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity


data class VendedorSeguido(
    @Embedded val followingdata: SeguidoEntity,
    @Relation(
        parentColumn = "vendedor",// la FK en SeguidoEntity
        entityColumn = "id"// la PK en VendedorEntity
    )
    val vendedor: VendedorEntity
)