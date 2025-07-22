package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritosDAO {
    @Query(" SELECT * FROM favoritos INNER JOIN productos ON favoritos.producto = productos.id")
    fun getFavoritos(): Flow<List<ProductoFavorito>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(favoritos: List<FavoritoEntity>)

}