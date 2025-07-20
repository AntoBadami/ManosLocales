package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.*
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDAO {
    @Query("SELECT * FROM productos")
    fun getAll(): Flow<List<ProductoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(productos: List<ProductoEntity>)

}