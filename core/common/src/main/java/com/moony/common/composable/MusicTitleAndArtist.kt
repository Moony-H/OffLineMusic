package com.moony.common.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicTitleAndArtist(
    title: String,
    artist: String,
    titleSize: Dp,
    artistTextSize: Dp,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = titleSize.value.sp,
            maxLines = 1,
            modifier = Modifier.basicMarquee(
                animationMode = MarqueeAnimationMode.Immediately,
                iterations = Int.MAX_VALUE
            )
        )
        Text(text = artist, fontSize = artistTextSize.value.sp, maxLines = 1)
    }
}

val edgeWidth = 32.dp
fun ContentDrawScope.drawFadedEdge(leftEdge: Boolean) {
    val edgeWidthPx = edgeWidth.toPx()
    drawRect(
        topLeft = Offset(if (leftEdge) 0f else size.width - edgeWidthPx, 0f),
        size = Size(edgeWidthPx, size.height),
        brush =
        Brush.horizontalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startX = if (leftEdge) 0f else size.width,
            endX = if (leftEdge) edgeWidthPx else size.width - edgeWidthPx
        ),
        blendMode = BlendMode.DstIn
    )
}

//@ExperimentalAnimationApi
//fun addAnimation(duration: Int = 800): ContentTransform {
//    return (slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
//        animationSpec = tween(durationMillis = duration)
//    )).togetherWith(slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
//        animationSpec = tween(durationMillis = duration)
//    ))
//}
