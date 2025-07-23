package com.tecmov2025.manoslocales.Utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.ProductoDTO
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.tecmov2025.manoslocales.Database.Entity.VendedorEntity
import com.tecmov2025.manoslocales.Database.POJO.ProductoConVendedor
import com.tecmov2025.manoslocales.Database.POJO.ProductoFavorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class ProductViewModel(private val repo: ApiRepository): ViewModel() {

    private val _historialProductos = mutableStateOf<List<ProductoConVendedor>>(emptyList())
    val historialProductos: State<List<ProductoConVendedor>> = _historialProductos


    val productoSeleccionado: ProductoConVendedor?
        get() = historialProductos.value.firstOrNull()

    private val _vendedorSeleccionado = mutableStateOf<VendedorEntity?>(null)


    val vendedorSeleccionado: State<VendedorEntity?> = _vendedorSeleccionado


    fun seleccionarVendedor(vendedor: VendedorEntity) {
        _vendedorSeleccionado.value = vendedor
    }

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

    fun productoEsFavorito(productoId: Int): Flow<Boolean>
    {
       return repo.productoEsFavorito(productoId)
    }

    fun añadirFavorito(productoId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repo.añadirFavorito(productoId)
        }
    }

    fun eliminarFavorito(productoId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            repo.eliminarFavorito(productoId)
        }
    }


}
