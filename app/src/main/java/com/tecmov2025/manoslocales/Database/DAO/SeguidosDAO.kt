package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tecmov2025.manoslocales.Database.POJO.VendedorSeguido
import kotlinx.coroutines.flow.Flow

@Dao
interface SeguidosDAO {

    @Transaction
    @Query("SELECT * FROM seguidos")
    fun obtenerVendedoresSeguidos(): Flow<List<VendedorSeguido>>

}