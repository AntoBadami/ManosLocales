package com.tecmov2025.manoslocales.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tecmov2025.manoslocales.Database.DAO.FavoritosDAO
import com.tecmov2025.manoslocales.Database.DAO.ProductosDAO
import com.tecmov2025.manoslocales.Database.DAO.SeguidosDAO
import com.tecmov2025.manoslocales.Database.DAO.UsuarioDAO
import com.tecmov2025.manoslocales.Database.DAO.VendedoresDAO
import com.tecmov2025.manoslocales.Database.Entity.Converters
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import com.tecmov2025.manoslocales.Database.Entity.SeguidoEntity
import com.tecmov2025.manoslocales.Database.Entity.UsuarioEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import java.util.concurrent.Executors

@Database(entities = [ProductoEntity::class,VendedorEntity::class, FavoritoEntity::class,
    UsuarioEntity::class, SeguidoEntity::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productosDao(): ProductosDAO
    abstract fun vendedoresDao(): VendedoresDAO
    abstract fun favoritosDao(): FavoritosDAO
    abstract fun usuarioDao(): UsuarioDAO
    abstract fun seguidosDao() : SeguidosDAO

    companion object {
        @Volatile private var instancia: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "manos_locales_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instancia = it }
            }
        }
    }
}
