package com.moony.common

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moony.resource.BackgroundBlack
import com.moony.resource.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun BlurredImage(bitmap: Bitmap?) {
    val albumBackgroundMaxSize = dimensionResource(R.dimen.max_size_album_background_image)
    val albumBackgroundMinSize = dimensionResource(R.dimen.min_size_album_background_image)


    //background
    bitmap?.asImageBitmap()?.let {
        Image(
            modifier = Modifier
                .sizeIn(
                    minWidth = albumBackgroundMinSize,
                    minHeight = albumBackgroundMinSize,
                    maxWidth = albumBackgroundMaxSize,
                    maxHeight = albumBackgroundMaxSize
                )
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .drawWithContent {
                    drawContent()
                    val fadeEffect =
                        Brush.verticalGradient(0f to BackgroundBlack, 0.5f to Color.Transparent)
                    drawRect(brush = fadeEffect, blendMode = BlendMode.DstIn)
                },
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SingleGradientTest(gradientColors: List<Color>) {

    Column(
        Modifier.padding(20.dp)
    ) {

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

        LaunchedEffect(progressAnimated) {

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
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
}
