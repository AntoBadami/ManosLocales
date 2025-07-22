package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface FavoritosDAO {
    @Query(" SELECT * FROM favoritos INNER JOIN productos ON favoritos.producto = productos.id")
    fun getFavoritos(): Flow<List<ProductoFavorito>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(favoritos: List<FavoritoEntity>)

    @Query("""
        SELECT EXISTS(
            SELECT 1 
            FROM favoritos 
            WHERE producto = :productoId
        )
    """)
    fun esFavorito( productoId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarFavorito(favorito: FavoritoEntity)

    @Query("DELETE FROM favoritos WHERE producto = :productoId")
    suspend fun eliminarFavorito(productoId: Int)

    @Query("""
        SELECT p.*, v.*
        FROM productos p
        INNER JOIN favoritos f ON p.id = f.producto
        INNER JOIN vendedores v ON p.vendedor = v.id
    """)
    fun getProductosFavoritosConVendedor(): Flow<List<ProductoConVendedor>>


}