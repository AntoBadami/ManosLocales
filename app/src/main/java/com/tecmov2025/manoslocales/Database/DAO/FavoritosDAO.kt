package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritosDAO {
    @Query(" SELECT * FROM favoritos INNER JOIN productos ON favoritos.producto = productos.id")
    fun getFavoritos(): Flow<List<ProductoFavorito>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(favoritos: List<FavoritoEntity>)

    @Query("SELECT COUNT(*) FROM favoritos WHERE producto = :productoId")
    fun esFavorito( productoId: Int): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun agregarFavorito(favorito: FavoritoEntity)

    @Query("DELETE FROM favoritos WHERE producto = :productoId")
    fun eliminarFavorito(productoId: Int):Int

    @Transaction
    @Query("SELECT * FROM productos WHERE id IN (SELECT producto FROM favoritos)")
    fun getProductosFavoritosConVendedor(): Flow<List<ProductoConVendedor>>

}
