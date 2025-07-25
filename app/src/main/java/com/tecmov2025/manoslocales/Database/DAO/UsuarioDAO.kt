package com.tecmov2025.manoslocales.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tecmov2025.manoslocales.Database.Entity.UsuarioEntity

@Dao
interface UsuarioDAO {

    @Query("SELECT * FROM UsuarioEntity LIMIT 1")
    fun getUsuario(): UsuarioEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun guardarUsuario(usuario: UsuarioEntity)

    // Cambiar contraseña
    @Query("UPDATE UsuarioEntity SET pass = :nuevaPass WHERE id = :id")
    fun actualizarPassword(id: Int, nuevaPass: String)

    // Cambiar email
    @Query("UPDATE UsuarioEntity SET email = :nuevoEmail WHERE id = :id")
    fun actualizarEmail(id: Int, nuevoEmail: String)

    // Cambiar nombre
    @Query("UPDATE UsuarioEntity SET nombre = :nuevoNombre WHERE id = :id")
    fun actualizarNombre(id: Int, nuevoNombre: String)

    // Cambiar apellido
    @Query("UPDATE UsuarioEntity SET apellido = :nuevoApellido WHERE id = :id")
    fun actualizarApellido(id: Int, nuevoApellido: String)
}