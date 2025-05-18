package com.tecmov2025.manoslocales.Utils

class ExampleProductList(
    val productosList: List<Producto> = List(20) { index ->
        when(index) {
            0 -> Producto(
                nombre = "Mate Imperial",
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
                nombre = "Mate Ranchero",
                descripcion = "Mate de algarrobo estilo ranchero.",
                precio = 3200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_737986-MLA84088296586_052025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_848919-MLA83470753618_042025-O.webp"),
                categoria = "mates",
                if (index%2 == 0) true else false
            )
            2 -> Producto(
                nombre = "Mate Algarrobo",
                descripcion = "Mate robusto de algarrobo camionero.",
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
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_796475-MLA83128788401_032025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_863085-MLA83128563407_032025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_884664-MLA83128788405_032025-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            5 -> Producto(
                nombre = "Vela Arcoiris",
                descripcion = "Vela decorativa multicolor tipo arcoiris.",
                precio = 1900.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_838803-MLA83527897558_042025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_686523-MLA83527858824_042025-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            6 -> Producto(
                nombre = "Vela Vaso",
                descripcion = "Vela en vaso de vidrio decorativo.",
                precio = 1600.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_666956-MLA73037890347_112023-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_736727-MLA72955537288_112023-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            7 -> Producto(
                nombre = "Canasto Mimbre",
                descripcion = "Canasto tejido en mimbre artesanal.",
                precio = 4200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_913630-MLA76516220482_052024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_705755-MLA76516220474_052024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_676540-MLA76516220472_052024-O.webp"),
                categoria = "canastos",
                if (index%2 == 0) true else false
            )
            8 -> Producto(
                nombre = "Bandeja",
                descripcion = "Bandeja tejida de mimbre.",
                precio = 2800.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_888031-MLA54654533373_032023-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_982313-MLA54657882390_032023-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_878091-MLA54657882382_032023-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            9 -> Producto(
                nombre = "Canasto Mixto",
                descripcion = "Canasto tejido mixto artesanal.",
                precio = 4300.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_826850-MLA74889796049_032024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_730639-MLA74756686742_032024-O.webp"),
                categoria = "canastos",
                if (index%2 == 0) true else false
            )
            10 -> Producto(
                nombre = "Individual",
                descripcion = "Individual tejido para mesa.",
                precio = 1200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_640917-MLA83492666433_042025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_965545-MLA83492364483_042025-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            11 -> Producto(
                nombre = "Cesto Soga",
                descripcion = "Cesto tejido en soga resistente.",
                precio = 3900.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_841396-MLA83201067392_042025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_800709-MLA83492051403_042025-O.webp"),
                categoria = "canastos",
                if (index%2 == 0) true else false
            )
            12 -> Producto(
                nombre = "Tabla de Madera",
                descripcion = "Tabla de madera natural para cocina.",
                precio = 2200.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_885418-MLA81480407816_012025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_822415-MLA81750594763_012025-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            13 -> Producto(
                nombre = "Tabla Redonda",
                descripcion = "Tabla redonda de madera natural.",
                precio = 2100.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_791190-MLA83132520721_032025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_996127-MLA83132715279_032025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_745331-MLA83132462777_032025-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            14 -> Producto(
                nombre = "Molinillo",
                descripcion = "Molinillo tradicional de madera.",
                precio = 1700.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_981155-MLU75794679206_042024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_759951-MLA83997334793_042025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_857109-MLA83703925972_042025-O.webp"),
                categoria = "cocina",
                if (index%2 == 0) true else false
            )
            15 -> Producto(
                nombre = "Suculentas",
                descripcion = "Suculentas en maceta decorativa.",
                precio = 2500.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_693544-MLA44198155927_112020-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_629589-MLA44198185017_112020-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_808972-MLA44198155842_112020-O.webp"),
                categoria = "plantas",
                if (index%2 == 0) true else false
            )
            16 -> Producto(
                nombre = "Cactus",
                descripcion = "Cactus decorativo en maceta.",
                precio = 2400.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_610921-MLA45788870073_052021-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_879753-MLA45788839458_052021-O.webp"),
                categoria = "plantas",
                if (index%2 == 0) true else false
            )
            17 -> Producto(
                nombre = "Bonsai",
                descripcion = "Bonsai decorativo para interiores.",
                precio = 3500.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_606165-MLA75763221754_042024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_640068-MLA75763349024_042024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_750521-MLA75763161676_042024-O.webp"),
                categoria = "plantas",
                if (index%2 == 0) true else false
            )
            18 -> Producto(
                nombre = "Vela Nudo",
                descripcion = "Vela decorativa de soja estilo nudo.",
                precio = 1800.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_878146-MLA76421431475_052024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_782800-MLA76421451533_052024-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_778379-MLA77606078684_072024-O.webp"),
                categoria = "velas",
                if (index%2 == 0) true else false
            )
            19 -> Producto(
                nombre = "Mate Camionero",
                descripcion = "Mate tradicional de calabaza camionero.",
                precio = 3100.0,
                ubicacion = "Córdoba, Argentina",
                images = listOf("https://http2.mlstatic.com/D_NQ_NP_651718-MLA83434823010_042025-O.webp",
                    "https://http2.mlstatic.com/D_NQ_NP_742857-MLA83435057022_042025-O.webp"),
                categoria = "mates",
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
