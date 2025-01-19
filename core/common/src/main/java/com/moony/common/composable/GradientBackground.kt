package com.moony.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    gradientBoxModifier: Modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f),
    colors: List<Color>,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        GradientBox(gradientBoxModifier, colors)
        content()
    }

}
