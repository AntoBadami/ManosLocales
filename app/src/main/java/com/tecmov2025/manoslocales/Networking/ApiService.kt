package com.tecmov2025.manoslocales.Networking

import retrofit2.http.GET

interface ApiService {
    @GET("productos")
    suspend fun getProductos(): List<ProductoDTO>

    @GET("vendedores")
    suspend fun getVendedores(): List<VendedorDTO>

}