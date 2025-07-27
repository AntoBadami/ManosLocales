package com.tecmov2025.manoslocales.Networking

import android.content.Context
import android.util.Log
import androidx.compose.ui.input.pointer.PointerEventPass
import com.tecmov2025.manoslocales.Database.AppDatabase
import com.tecmov2025.manoslocales.Database.Entity.FavoritoEntity
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import com.tecmov2025.manoslocales.Database.Entity.UsuarioEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import com.tecmov2025.manoslocales.SharedPreferences.CONFIG_TIEMPO
import com.tecmov2025.manoslocales.SharedPreferences.ConfigNames
import com.tecmov2025.manoslocales.SharedPreferences.ConfigPreferences
import com.tecmov2025.manoslocales.Utils.toEntity
import com.tecmov2025.manoslocales.Utils.toEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ApiRepository(private val api: ApiService,private val database: AppDatabase)
{

    private val productosDao = database.productosDao()
    private val vendedoresDao = database.vendedoresDao()
    private val favoritosDao = database.favoritosDao()
    private val usuarioDao = database.usuarioDao()

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

    suspend fun loginyGuardarUsuario(email : String, pass: String): Boolean
    {
       return try {
            val usuarioDTO = UsuarioDTO(email = email, pass = pass)
            val response = api.postUsuario(usuarioDTO)

            if (response.isSuccessful) {
                val usuario = response.body()
                if(usuario != null)
                {
                    val UsuarioEntity = usuario.toEntity()
                    usuarioDao.guardarUsuario(UsuarioEntity)
                    Log.d("ApiRepository", "Guardados los datos del usuario")
                    return true
                }
                return false

            } else {
                return false
            }
        }catch (e: Exception) {
            Log.e("ApiRepository", "Error al obtener o guardar usuario", e)
           return false
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

    fun obtenerFavoritosDB(): Flow<List<ProductoConVendedor>> {
        return favoritosDao.getProductosFavoritosConVendedor()
    }
    fun obtenerUsuarioDB(): Flow<UsuarioEntity?> {
        return usuarioDao.getUsuario()
    }

    fun actualizarUsuarioDB(usuarioEntity: UsuarioEntity) {
        return usuarioDao.guardarUsuario(usuarioEntity)
    }


    fun productoEsFavorito(productoId : Int): Flow<Boolean>{
        return favoritosDao.esFavorito(productoId)
            .map { count -> count > 0 }
            .distinctUntilChanged()
    }

    fun añadirFavorito(productoId : Int)
    {
        favoritosDao.agregarFavorito(FavoritoEntity(productoId))
    }

    fun eliminarFavorito(productoId: Int)
    {
        favoritosDao.eliminarFavorito(productoId)
    }


    // Funciones de configuracion

    fun sesionEstaAbierta(context: Context): Boolean
    {
        val config = ConfigPreferences(context)
        return config.isLoggedIn()
    }

    fun cerrarSesion(context: Context)
    {
        val config = ConfigPreferences(context)
        config.clearSession()
    }
    fun establecerSesionIniciada(context: Context)
    {
        val config = ConfigPreferences(context)
        config.setLoggedIn()
    }

    fun establecerTiempoNotificaciones(context: Context, tiempo: CONFIG_TIEMPO)
    {
        val config = ConfigPreferences(context)
        config.setTiempoNotificaciones(tiempo)
    }
    fun getTiempoNotificaciones(context: Context): CONFIG_TIEMPO
    {
        val config = ConfigPreferences(context)
        return config.getTiempoNotificacionesConfig()
    }
    fun permisosInicializados(context: Context): Boolean
    {
        val config = ConfigPreferences(context)
        return config.permisosInicializados()
    }

    fun establecerPermisosInicializados(context: Context)
    {
        val config = ConfigPreferences(context)
        return config.establecerInicializadosLosPermisos()
    }
}