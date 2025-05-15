package com.tecmov2025.manoslocales.Utils

class ExampleProductList(
    val productosList: List<Producto> = List(18) { index ->
        when(index) {
            0 -> Producto(
                nombre = "Mate Calabaza Imperial",
                descripcion = "Mate tradicional de calabaza imperial.",
                precio = 3500.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_645828-MLA73262998323_122023-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_2X_720669-MLA73262929367_122023-F.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_810338-MLA73177838120_122023-O.webp"),
                categoria = "mates",
                if (index%2 == 0) true else false
            )
            1 -> Producto(
                nombre = "Mate Algarrobo Ranchero",
                descripcion = "Mate de algarrobo estilo ranchero.",
                precio = 3200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_737986-MLA84088296586_052025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_848919-MLA83470753618_042025-O.webp"),
                categoria = "mates",
                if (index%2 == 0) true else false
            )
            2 -> Producto(
                nombre = "Mate Algarrobo Camionero",
                descripcion = "Mate robusto de algarrobo para camioneros.",
                precio = 3000.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_796078-MLA84126103516_052025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_712385-MLA84422424103_052025-O.webp"),
                categoria = "mates",
                if (index%2 == 0) true else false
            )
            3 -> Producto(
                nombre = "Dulce de Leche",
                descripcion = "Dulce de leche tradicional argentino.",
                precio = 1500.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_732813-MLA83587983311_042025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_872179-MLA83587953491_042025-O.webp"),
                categoria = "alimentos",
                if (index%2 == 0) true else false
            )
            4 -> Producto(
                nombre = "Vela Burbujas",
                descripcion = "Vela decorativa con forma de burbujas.",
                precio = 1800.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_796475-MLA83128788401_032025-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            5 -> Producto(
                nombre = "Vela Arcoiris",
                descripcion = "Vela decorativa multicolor tipo arcoiris.",
                precio = 1900.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_838803-MLA83527897558_042025-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            6 -> Producto(
                nombre = "Vela Vaso",
                descripcion = "Vela en vaso de vidrio decorativo.",
                precio = 1600.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_666956-MLA73037890347_112023-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            7 -> Producto(
                nombre = "Canasto Mimbre",
                descripcion = "Canasto tejido en mimbre artesanal.",
                precio = 4200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_913630-MLA76516220482_052024-O.webp"),
                categoria = "canastos",
                if (index%2 == 0) true else false
            )
            8 -> Producto(
                nombre = "Bandeja",
                descripcion = "Bandeja decorativa de madera.",
                precio = 2800.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_805796-MLA69382627574_052023-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            9 -> Producto(
                nombre = "Canasto Mixto",
                descripcion = "Canasto tejido mixto artesanal.",
                precio = 4300.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_826850-MLA74889796049_032024-O.webp"),
                categoria = "canastos",
                if (index%2 == 0) true else false
            )
            10 -> Producto(
                nombre = "Individual",
                descripcion = "Individual tejido para mesa.",
                precio = 1200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_640917-MLA83492666433_042025-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            11 -> Producto(
                nombre = "Cesto Soga",
                descripcion = "Cesto tejido en soga resistente.",
                precio = 3900.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_841396-MLA83201067392_042025-O.webp"),
                categoria = "canastos",
                if (index%2 == 0) true else false
            )
            12 -> Producto(
                nombre = "Tabla de Madera",
                descripcion = "Tabla de madera natural para cocina.",
                precio = 2200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_822415-MLA81750594763_012025-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            13 -> Producto(
                nombre = "Tabla Redonda",
                descripcion = "Tabla redonda de madera natural.",
                precio = 2100.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_813221-MLA74154125737_012024-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            14 -> Producto(
                nombre = "Molinillo",
                descripcion = "Molinillo tradicional de madera.",
                precio = 1700.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_981155-MLU75794679206_042024-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            15 -> Producto(
                nombre = "Suculentas",
                descripcion = "Suculentas en maceta decorativa.",
                precio = 2500.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_883338-MLA26983317675_032018-O.webp"),
                categoria = "plantas",
                if (index%2 == 0) true else false
            )
            16 -> Producto(
                nombre = "Cactus",
                descripcion = "Cactus decorativo en maceta.",
                precio = 2400.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_610921-MLA45788870073_052021-O.webp"),
                categoria = "plantas",
                if (index%2 == 0) true else false
            )
            17 -> Producto(
                nombre = "Bonsai",
                descripcion = "Bonsai decorativo para interiores.",
                precio = 3500.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_711871-MLA69801153454_062023-O.webp"),
                categoria = "plantas",
                if (index%2 == 0) true else false
            )
            else -> Producto(
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
        }
    }
)
