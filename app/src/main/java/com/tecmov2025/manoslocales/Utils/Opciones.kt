package com.tecmov2025.manoslocales.Utils

import androidx.compose.ui.graphics.vector.ImageVector

data class Opcion (
    val text: String,
    val onclick : ()-> Unit = {},
    val icon: ImageVector? = null
)
