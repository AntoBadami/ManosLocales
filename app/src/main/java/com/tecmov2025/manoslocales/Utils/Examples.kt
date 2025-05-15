package com.tecmov2025.manoslocales.Utils

class ExampleProductList(
    val productosList: List<Producto> = List(20) { index ->
        Producto(
            nombre = "Producto $index",
            descripcion = "Descripción del producto $index",
            precio = index * 3.0,
            ubicacion = "Córdoba, Argentina",
            images = listOf(
                "https://acdn-us.mitiendanube.com/stores/001/640/893/products/sorteo-ms-5-70852b318a3a38277f17279601997418-640-0.webp",
                "https://acdn-us.mitiendanube.com/stores/001/640/893/products/9c663a0f-7169-4ce0-9079-84a0dc7594d21-07f82bdece7591b8ed16753829200230-640-0.webp"
            ),
            categoria = "mates",
            if (index%2 == 0) true else false
        )
    })