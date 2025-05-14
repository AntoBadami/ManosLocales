package com.tecmov2025.manoslocales.Utils

/** Clase producto*/
data class Producto(val nombre: String,
                    val descripcion: String,
                    val precio : Double,
                    val ubicacion : String,
                    val images: List<String>,
                    var favoritoState : Boolean = false)

/** Opcion del drawer en la barra de busqueda*/
data class Opcion (val text: String, val onclicick : ()-> Unit = {},val tipo : TipoOpcion = TipoOpcion.NORMAL)
enum class TipoOpcion{NORMAL,SWITCH,LIST}
