package com.tecmov2025.manoslocales.Utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.Networking.ProductoDTO
import kotlinx.coroutines.launch
import androidx.compose.runtime.State


class ProductViewModel(private val repo: ApiRepository): ViewModel() {

    private val _historialProductos = mutableStateOf<List<ProductoDTO>>(emptyList())
    val historialProductos: State<List<ProductoDTO>> = _historialProductos


    private val _productos = mutableStateOf<List<ProductoDTO>>(emptyList())
    val productos: State<List<ProductoDTO>> = _productos //lectura

    val productoSeleccionado: ProductoDTO?
        get() = historialProductos.value.firstOrNull()

    init {
        cargarProductos()
    }

    fun seleccionarProducto(producto: ProductoDTO) {
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



}
