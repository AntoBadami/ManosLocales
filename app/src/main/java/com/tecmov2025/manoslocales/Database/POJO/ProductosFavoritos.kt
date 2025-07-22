package com.tecmov2025.manoslocales.Database.POJO

import androidx.room.Embedded
import androidx.room.Relation
import com.tecmov2025.manoslocales.Database.Entity.FavoritosEntity
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity

data class ProductosFavoritosdata(
    @Embedded val favorito: FavoritosEntity,
    @Relation(
        parentColumn = "producto",  // la FK en FavoritoEntity
        entityColumn = "id"           // la PK en ProductoEntity
    )
    val producto: ProductoEntity
)