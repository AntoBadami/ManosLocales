package com.tecmov2025.manoslocales

/** Clase producto*/
data class Producto(val nombre: String,
                    val descripcion: String,
                    val precio : Double,
                    val image : Int = R.drawable.ic_launcher_foreground)

/** Opcion del drawer en la barra de busqueda*/
data class Opcion (val text: String, val onclicick : ()-> Unit)
