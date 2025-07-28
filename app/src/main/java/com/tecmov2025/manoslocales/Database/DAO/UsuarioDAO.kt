package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tecmov2025.manoslocales.Database.Entity.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDAO {

    @Query("SELECT * FROM UsuarioEntity LIMIT 1")
    fun getUsuario(): Flow<UsuarioEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun guardarUsuario(usuario: UsuarioEntity)
}
