package com.tecmov2025.manoslocales.Networking

import com.google.gson.annotations.SerializedName

data class ProductoDTO(
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
    val vendedor: String,
    @SerializedName("esFavorito")
    var favoritoState : Boolean = false,
    @SerializedName("email")
    val email: String,
    @SerializedName("telefono")
    val telefono: String
)