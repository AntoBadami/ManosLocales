package com.tecmov2025.manoslocales.Networking

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("productos")
    suspend fun getProductos(): List<ProductoDTO>

    @GET("vendedores")
    suspend fun getVendedores(): List<VendedorDTO>

    @GET("favoritos")
    suspend fun getFavoritos(): List<FavoritoDTO>

    @POST("/login")
    suspend fun postUsuario(@Body usuario: UsuarioDTO): Response<UsuarioDTO>
}
