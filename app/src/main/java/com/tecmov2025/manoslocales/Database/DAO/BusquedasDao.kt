package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tecmov2025.manoslocales.Database.Entity.BusquedaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusquedasDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(busqueda: BusquedaEntity)

    @Query("SELECT * FROM busquedas ORDER BY id DESC LIMIT 3")
    fun obtenerUltimos(): Flow<List<BusquedaEntity>>
}
