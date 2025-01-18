package com.moony.music_player.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moony.common.composable.GradientBox

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    gradientBoxModifier: Modifier = Modifier.fillMaxSize(),
    colors: List<Color>,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        GradientBox(gradientBoxModifier, colors)
        content()
    }

}
