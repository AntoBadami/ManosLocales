package com.tecmov2025.manoslocales.Networking

import com.google.gson.annotations.SerializedName

data class ProductoDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("precio")
    val precio : Double,
    @SerializedName("ubicacion")
    val ubicacion : String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("vendedor")
    val vendedor: Int,
    @SerializedName("esFavorito")
    var favoritoState : Boolean = false,
    @SerializedName("email")
    val email: String,
    @SerializedName("telefono")
    val telefono: String
)

data class VendedorDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("perfilFoto")
    val perfilFoto: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("telefono")
    val telefono: String,
    @SerializedName("ubicacion")
    val ubicacion: String
)