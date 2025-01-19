package com.moony.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.moony.resource.BackgroundBlack

private val darColorScheme = darkColorScheme(
    primary = BackgroundBlack,
    primaryContainer = BackgroundBlack,
    onPrimaryContainer = BackgroundBlack,
    onPrimary = Color.White,
    surface = BackgroundBlack,
    background = BackgroundBlack
)

private val lightColorScheme = lightColorScheme(
    primary = BackgroundBlack,
    primaryContainer = BackgroundBlack,
    onPrimaryContainer = BackgroundBlack,
    onPrimary = Color.White,
    surface = BackgroundBlack,
    background = BackgroundBlack
)

@Composable
fun OffLineMusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = false)
    systemUiController.setNavigationBarColor(color = BackgroundBlack, darkIcons = false)

    val colorScheme = if (darkTheme) darColorScheme else lightColorScheme


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes(),
        content = {
            content()
        }
    )
}
