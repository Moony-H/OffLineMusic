package com.moony.common.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import com.moony.resource.BackgroundBlack


@Composable
fun GradientBox(modifier: Modifier, gradientColors: List<Color>) {
    if (gradientColors.isEmpty()) return
    if (gradientColors.size < 2) {
        Box(modifier = modifier
            .aspectRatio(1f)
            .background(color = gradientColors.first())
            .drawWithContent {
                val fadeEffect =
                    Brush.verticalGradient(
                        0f to BackgroundBlack,
                        3f to Color.Transparent,
                        tileMode = TileMode.Mirror
                    )
                drawRect(brush = fadeEffect, blendMode = BlendMode.DstIn)
                drawContent()
            }
        )
        return
    }
    val limit = 1f
    val transition = rememberInfiniteTransition(label = "shimmer")
    val progressAnimated by transition.animateFloat(
        initialValue = -limit,
        targetValue = limit,
        animationSpec = infiniteRepeatable(
            animation = tween(30000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "shimmer"
    )
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .drawWithCache {
                val width = 8000f
                val offset = width * progressAnimated
                val brush = Brush.linearGradient(
                    colors = gradientColors,
                    start = Offset(offset, width / 2),
                    end = Offset(offset + width, width / 2),
                    tileMode = TileMode.Mirror
                )

                onDrawBehind {
                    drawRect(
                        brush = brush,
                        blendMode = BlendMode.SrcIn
                    )

                    val fadeEffect =
                        Brush.verticalGradient(
                            0f to BackgroundBlack,
                            3f to Color.Transparent,
                            tileMode = TileMode.Mirror
                        )
                    drawRect(brush = fadeEffect, blendMode = BlendMode.DstIn)

                }
            }

    )
}
