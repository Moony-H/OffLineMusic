package com.moony.offlinemusic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.moony.resource.BackgroundBlack

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun OffLineMusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = false)

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = BackgroundBlack,
            primaryContainer = BackgroundBlack,
            onPrimaryContainer = BackgroundBlack,
            onPrimary = Color.White,
            surface = BackgroundBlack,
            background = BackgroundBlack
        )
    } else {
        lightColorScheme(
            primary = BackgroundBlack,
            primaryContainer = BackgroundBlack,
            onPrimaryContainer = BackgroundBlack,
            onPrimary = Color.White,
            surface = BackgroundBlack,
            background = BackgroundBlack
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes(),
        content = {
            content()
        }
    )
}
