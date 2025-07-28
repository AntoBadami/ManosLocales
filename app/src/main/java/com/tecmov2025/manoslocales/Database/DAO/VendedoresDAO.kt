package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VendedoresDAO {

    @Query("SELECT * FROM vendedores")
    fun getAll(): Flow<List<VendedorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vendedores: List<VendedorEntity>)

    @Query("SELECT * FROM vendedores WHERE nombre LIKE '%' || :query || '%'")
    fun buscarPorNombre(query: String): Flow<List<VendedorEntity>>
}
