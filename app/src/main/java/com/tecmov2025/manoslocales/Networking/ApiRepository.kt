package com.tecmov2025.manoslocales.Networking

import android.util.Log
import com.tecmov2025.manoslocales.Database.AppDatabase
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import com.tecmov2025.manoslocales.Utils.toEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class ApiRepository(private val api: ApiService,private val database: AppDatabase)
{

    private val productosDao = database.productosDao()
    private val vendedoresDao = database.vendedoresDao()
    private val favoritosDao = database.favoritosDao()

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

    suspend fun obteneryGuardarVendedores() {
        try {
            val vendedoresDTO = api.getVendedores()
            val vendedoresEntity = vendedoresDTO.toEntityList()
            vendedoresDao.insertAll(vendedoresEntity)
        } catch (e: Exception) {
            Log.e("ApiRepository", "Error al obtener o guardar vendedores", e)
        }
    }

    suspend fun obteneryGuardarFavoritos() {
        try {
            val favoritosDTO = api.getFavoritos()
            val favoritosEntity = favoritosDTO.toEntityList()
            favoritosDao.insertAll(favoritosEntity)

        } catch (e: Exception) {
            Log.e("ApiRepository", "Error al obtener o guardar favoritos", e)
        }
    }

    fun obtenerProductosDB(): Flow<List<ProductoEntity>> {
        return productosDao.getAll()
    }

    fun obtenerProductosConVendedorDB(): Flow<List<ProductoConVendedor>> {
        return productosDao.getAllProductosConVendedor()
    }

    fun obtenerProductosPorVendedorDB(vendedorID : Int): Flow<List<ProductoConVendedor>> {
        return productosDao.obtenerProductosPorVendedor(vendedorID)
    }
    fun obtenerVendedoresDB(): Flow<List<VendedorEntity>> {
        return vendedoresDao.getAll()
    }

    fun obtenerFavoritosDB(): Flow<List<ProductoFavorito>> {
        return favoritosDao.getFavoritos()
    }

    fun productoEsFavorito(productoId : Int): Flow<Boolean>{
        return  favoritosDao.esFavorito(productoId)
    }

    suspend fun añadirFavorito(productoId : Int)
    {
        favoritosDao.agregarFavorito(FavoritoEntity(productoId))
    }

    suspend fun eliminarFavorito(productoId: Int)
    {
        favoritosDao.eliminarFavorito(productoId)
    }




}