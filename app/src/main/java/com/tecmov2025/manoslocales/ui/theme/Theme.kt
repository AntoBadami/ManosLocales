package com.tecmov2025.manoslocales.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ButtonColor,
    secondary = SecondColor,
    tertiary = Pink40,
    background = BackgroundColor,
    surface = BackgroundColor
)

private val LightColorScheme = lightColorScheme(
    primary = ButtonColor,
    secondary = SecondColor,
    tertiary = Pink40,
    background = BackgroundColor,
    surface = BackgroundColor
)

@Composable
fun ManosLocalesTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}