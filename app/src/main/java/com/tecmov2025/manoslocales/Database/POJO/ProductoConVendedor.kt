package com.tecmov2025.manoslocales.Database.POJO

import androidx.room.Embedded
import androidx.room.Relation
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity

data class ProductoConVendedor(
    @Embedded val producto: ProductoEntity,
    @Relation(
        parentColumn = "vendedor",  // la FK en ProductoEntity
        entityColumn = "id"           // la PK en VendedorEntity
    )
    val vendedor: VendedorEntity
)