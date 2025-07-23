package com.tecmov2025.manoslocales.Database.POJO

import androidx.room.Embedded
import androidx.room.Relation
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity

data class ProductoFavorito(
    @Embedded val favorito: FavoritoEntity,
    @Relation(
        parentColumn = "producto",  // la FK en FavoritoEntity
        entityColumn = "id"           // la PK en ProductoEntity
    )
    val producto: ProductoEntity
)