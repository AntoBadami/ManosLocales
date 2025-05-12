package com.tecmov2025.manoslocales

/** Clase producto*/
data class Producto(val nombre: String,
                    val descripcion: String,
                    val precio : Double,
                    val ubicacion : String,
                    val images: List<String>  )

/** Opcion del drawer en la barra de busqueda*/
data class Opcion (val text: String, val onclicick : ()-> Unit)
