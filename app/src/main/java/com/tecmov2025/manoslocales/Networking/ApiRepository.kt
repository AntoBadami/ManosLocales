package com.tecmov2025.manoslocales.Networking

class ApiRepository(private val api: ApiService)
{
    suspend fun obtenerProductos(): List<ProductoDTO>
    {
        return api.getProductos()
    }
}