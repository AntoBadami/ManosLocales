package com.tecmov2025.manoslocales.Utils

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.ProductoDTO
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.tecmov2025.manoslocales.Database.Entity.UsuarioEntity
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import com.tecmov2025.manoslocales.Notifications.NotificationHandler
import com.tecmov2025.manoslocales.SharedPreferences.CONFIG_TIEMPO
import com.tecmov2025.manoslocales.SharedPreferences.ConfigPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn


class ProductViewModel(private val repo: ApiRepository): ViewModel() {

    var sesionEstaAbierta by mutableStateOf<Boolean?>(null)
        private set

    private val _historialProductos = mutableStateOf<List<ProductoConVendedor>>(emptyList())
    val historialProductos: State<List<ProductoConVendedor>> = _historialProductos


    val productoSeleccionado: ProductoConVendedor?
        get() = historialProductos.value.firstOrNull()

    private val _vendedorSeleccionado = mutableStateOf<VendedorEntity?>(null)
    val vendedorSeleccionado: State<VendedorEntity?> = _vendedorSeleccionado


    private val _snackbarMessage = MutableStateFlow("")
    val snackbarMessage: StateFlow<String> = _snackbarMessage.asStateFlow()


    var loginStatus by mutableStateOf<Boolean?>(null)
        private set


    private val _tiempoNotificaciones = MutableStateFlow(CONFIG_TIEMPO.NUNCA)
    val tiempoNotificaciones = _tiempoNotificaciones.asStateFlow()

    val usuario: StateFlow<UsuarioEntity?> = repo.obtenerUsuarioDB()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    val productosConVendedorState: StateFlow<List<ProductoConVendedor>> =
        repo
            .obtenerProductosConVendedorDB()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )


    val vendedoresState: StateFlow<List<VendedorEntity>> =
        repo
            .obtenerVendedoresDB()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )

    val favoritosState: StateFlow<List<ProductoConVendedor>> =
        repo
            .obtenerFavoritosDB()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

    fun showMessage(msg: String) {
        _snackbarMessage.value = msg
    }


    fun seleccionarVendedor(vendedor: VendedorEntity) {
        _vendedorSeleccionado.value = vendedor
    }


    fun seleccionarProducto(producto: ProductoConVendedor) {
        _historialProductos.value = listOf(producto) + _historialProductos.value
    }

    fun desapilarProducto() {
        if (_historialProductos.value.isNotEmpty()) {
            _historialProductos.value = _historialProductos.value.drop(1)
        }
    }


    fun sincronizarBaseDeDatos() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.obteneryGuardarVendedores()
            repo.obteneryGuardarProductos()
            repo.obteneryGuardarFavoritos()
        }
    }

    fun productosDeUnMismoVendedor(vendedorId: Int): StateFlow<List<ProductoConVendedor>> =
        repo
            .obtenerProductosPorVendedorDB(vendedorId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )

    fun productoEsFavorito(productoId: Int): Flow<Boolean> {
        return repo.productoEsFavorito(productoId)
    }

    fun añadirFavorito(productoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.añadirFavorito(productoId)
        }
    }

    fun eliminarFavorito(productoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.eliminarFavorito(productoId)
        }
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO)
        {
            loginStatus = repo.loginyGuardarUsuario(email, pass)
        }
    }

    fun establecerSesionIniciada(context: Context) {
        viewModelScope.launch(Dispatchers.IO){ repo.establecerSesionIniciada(context) }
    }

    fun cerrarSesion(context: Context) {
        sesionEstaAbierta = false
        viewModelScope.launch(Dispatchers.IO){ repo.cerrarSesion(context) }
    }

    fun verificarSesion(context: Context) {
        viewModelScope.launch(Dispatchers.IO){
            sesionEstaAbierta = repo.sesionEstaAbierta(context)
        }
    }

    fun actualizarUsuario(nombre: String, apellido: String, email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usuario.value?.let { actual ->
                val actualizado = actual.copy(
                    nombre = nombre,
                    apellido = apellido,
                    email = email,
                    pass = pass
                )
                repo.actualizarUsuarioDB(actualizado)
            }
        }
    }


    fun establecerTiempoNotificaciones(context: Context, tiempo: CONFIG_TIEMPO = CONFIG_TIEMPO.H6)
    {
        NotificationHandler.setPeriodicNotificationTime(context, tiempo)
        _tiempoNotificaciones.value = tiempo
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.establecerTiempoNotificaciones(context,tiempo)
        }
    }

    fun obtenerTiempoNotificaciones(context: Context)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val tiempo = repo.getTiempoNotificaciones(context)
            _tiempoNotificaciones.value = tiempo
        }

    }

    fun InicializarNotificacionesSiEsNecesario(context: Context)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            if(!repo.permisosInicializados(context))
            {
                repo.establecerPermisosInicializados(context)
                NotificationHandler.createChannel(context)
                establecerTiempoNotificaciones(context)
            }
        }
    }

    fun InicializarNotificaciones(context: Context, autorizacionUsuario : Boolean = true)
    {
        if(autorizacionUsuario)
        {
            NotificationHandler.createChannel(context)
            establecerTiempoNotificaciones(context)
        }
        else
        {
            NotificationHandler.createChannel(context)
            establecerTiempoNotificaciones(context, CONFIG_TIEMPO.NUNCA)
        }
        repo.establecerPermisosInicializados(context)
    }


}
