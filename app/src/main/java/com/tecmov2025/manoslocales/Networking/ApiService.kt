package com.tecmov2025.manoslocales.Networking

import android.R
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("productos")
    suspend fun getProductos(): List<ProductoDTO>

    @GET("vendedores")
    suspend fun getVendedores(): List<VendedorDTO>
}