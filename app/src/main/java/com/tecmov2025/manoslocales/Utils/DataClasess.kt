package com.tecmov2025.manoslocales.Utils

import androidx.compose.ui.graphics.vector.ImageVector

/** Clase producto*/
data class Producto(val nombre: String,
                    val descripcion: String,
                    val precio : Double,
                    val ubicacion : String,
                    val images: List<String>,
                    val categoria: String,
                    val vendedor: String,
                    var favoritoState : Boolean = false,
                    val email: String,
                    val telefono: String
    )
/** Clase vendedores ejemplos */

data class Vendedor(val nombre: String,
                    val descripcion: String,
                    val perfilFoto: String,
                    val email: String,
                    val telefono: String,
                    val ubicacion: String
)

/** Opcion del drawer en la barra de busqueda*/
data class Opcion (
    val text: String,
    val onclicick : ()-> Unit = {},
    val tipo : TipoOpcion = TipoOpcion.NORMAL,
    val icon: ImageVector? = null
)
enum class TipoOpcion{NORMAL,SWITCH,LIST}
