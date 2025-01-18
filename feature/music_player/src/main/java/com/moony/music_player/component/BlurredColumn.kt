package com.moony.music_player.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.moony.resource.BackgroundBlack
import com.moony.resource.R

@Composable
fun BlurredColumn(
    bitmap: Bitmap?,
    horizontalAlignment: Alignment.Horizontal,
    modifier: Modifier = Modifier,

    content: @Composable ColumnScope.() -> Unit
) {
    val albumBackgroundMaxSize = dimensionResource(R.dimen.max_size_album_background_image)
    val albumBackgroundMinSize = dimensionResource(R.dimen.min_size_album_background_image)

    Box(modifier = modifier) {
        //background
        bitmap?.asImageBitmap()?.let {
            Image(
                modifier = Modifier
                    .sizeIn(
                        minWidth = albumBackgroundMinSize,
                        minHeight = albumBackgroundMinSize,
                        maxWidth = albumBackgroundMaxSize,
                        maxHeight = albumBackgroundMaxSize
                    ).graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
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
        Column(
            modifier = Modifier.fillMaxSize(),
            content = content,
            horizontalAlignment = horizontalAlignment
        )
    }
}
