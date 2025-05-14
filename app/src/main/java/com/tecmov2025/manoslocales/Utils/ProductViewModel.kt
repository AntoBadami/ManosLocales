package com.tecmov2025.manoslocales.Utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProductViewModel: ViewModel() {
    var productoSeleccionado by mutableStateOf<Producto?>(null)
    fun seleccionarProducto(producto: Producto) {
        productoSeleccionado = producto
    }
}