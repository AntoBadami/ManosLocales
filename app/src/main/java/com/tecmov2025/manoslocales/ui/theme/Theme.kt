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
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/*No se utiliza por el momento*/
private val DarkColorScheme = darkColorScheme(
    primary = AppOrange,
    secondary = SecondColor,
    tertiary = Pink40,
    background = BackgroundColor,
    surface = BackgroundColor
)

private val LightColorScheme = lightColorScheme(
    primary = AppOrange,
    secondary = SecondColor,
    tertiary = Pink40,
    background = BackgroundColor,
    surface = BackgroundColor
)

@Composable
fun ManosLocalesTheme(
    //useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    SetStatusBarColor()
    val colors = LightColorScheme
    val statusBarColor = AppOrange

    Activity().window.apply {}

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

@Composable
fun SetStatusBarColor() {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = AppOrange.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
    }
}