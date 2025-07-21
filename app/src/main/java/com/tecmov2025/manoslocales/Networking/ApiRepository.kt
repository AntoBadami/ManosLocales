package com.tecmov2025.manoslocales.Networking

import android.util.Log
import com.tecmov2025.manoslocales.Database.AppDatabase
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import com.tecmov2025.manoslocales.Utils.toEntityList
import kotlinx.coroutines.flow.Flow

class ApiRepository(private val api: ApiService,private val database: AppDatabase)
{

    private val productosDao = database.productosDao()
    private val vendedoresDao = database.vendedoresDao()
    suspend fun obtenerProductos(): List<ProductoDTO>
    {
        return api.getProductos()
    }

    suspend fun obteneryGuardarProductos() {
        try {
            val productosDTO = api.getProductos()
            val productosEntity = productosDTO.toEntityList()
            productosDao.insertAll(productosEntity)
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error al obtener o guardar productos", e)
        }
    }

    fun obtenerProductosDB(): Flow<List<ProductoEntity>> {
        return productosDao.getAll()
    }

    suspend fun obteneryGuardarVendedores() {
        try {
            val vendedoresDTO = api.getVendedores()
            val vendedoresEntity = vendedoresDTO.toEntityList()
            vendedoresDao.insertAll(vendedoresEntity)
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error al obtener o guardar productos", e)
        }
    }

    fun obtenerVendedoresDB(): Flow<List<VendedorEntity>> {
        return vendedoresDao.getAll()
    }


}