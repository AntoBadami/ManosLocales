package com.tecmov2025.manoslocales.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tecmov2025.manoslocales.Database.DAO.ProductosDAO
import com.tecmov2025.manoslocales.Database.Entity.Converters
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity

@Database(entities = [ProductoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productosDao(): ProductosDAO

    companion object {
        @Volatile private var instancia: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "manos_locales_database"
                ).build().also { instancia = it }
            }
        }
    }
}
