package com.tecmov2025.manoslocales.ActivityFavoritos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.tecmov2025.manoslocales.ui.theme.ManosLocalesTheme

class FavoritosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManosLocalesTheme{ FavoritosNavigation() }
        }
    }

}
