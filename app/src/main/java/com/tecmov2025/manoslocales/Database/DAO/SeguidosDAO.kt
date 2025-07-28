package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tecmov2025.manoslocales.Database.Entity.SeguidoEntity
import com.tecmov2025.manoslocales.Database.POJO.VendedorSeguido
import kotlinx.coroutines.flow.Flow

@Dao
interface SeguidosDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun seguirVendedor(seguido: SeguidoEntity)

    @Transaction
    @Query("SELECT * FROM seguidos")
    fun obtenerVendedoresSeguidos(): Flow<List<VendedorSeguido>>

    @Delete
    fun eliminarSeguido(seguido: SeguidoEntity)


}