package com.tecmov2025.manoslocales.Utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.ProductoDTO
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.tecmov2025.manoslocales.Database.Entity.ProductoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class ProductViewModel(private val repo: ApiRepository): ViewModel() {

    private val _historialProductos = mutableStateOf<List<ProductoEntity>>(emptyList())
    val historialProductos: State<List<ProductoEntity>> = _historialProductos


    private val _productos = mutableStateOf<List<ProductoDTO>>(emptyList())
    val productos: State<List<ProductoDTO>> = _productos //lectura

    val productoSeleccionado: ProductoEntity?
        get() = historialProductos.value.firstOrNull()

    // TODO Reemplazar nombre por productos cuando finalice integracion de networking y db
    val productosState: StateFlow<List<ProductoEntity>> =
        repo
            .obtenerProductosDB()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )

    init {
        cargarProductos()
    }

    fun seleccionarProducto(producto: ProductoEntity) {
        _historialProductos.value = listOf(producto) + _historialProductos.value
    }

    fun desapilarProducto() {
        if (_historialProductos.value.isNotEmpty()) {
            _historialProductos.value = _historialProductos.value.drop(1)
        }
    }


    private fun cargarProductos()
    {
        viewModelScope.launch{
            val lista = repo.obtenerProductos()  // suspend fun que trae datos
            _productos.value = lista  // actualiza
        }
    }

    fun sincronizarProductos() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.obteneryGuardarProductos()
        }
    }



}
