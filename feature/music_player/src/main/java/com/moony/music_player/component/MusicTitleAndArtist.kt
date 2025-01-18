package com.moony.music_player.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moony.resource.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicTitleAndArtist(title: String, artist: String, modifier: Modifier = Modifier) {
    val titleSize = dimensionResource(R.dimen.text_music_title_size).value.sp
    val artistTextSize = dimensionResource(R.dimen.text_music_artist_size).value.sp
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = titleSize,
            maxLines = 1,
            modifier = Modifier.basicMarquee(animationMode = MarqueeAnimationMode.Immediately, iterations = Int.MAX_VALUE)
        )
        Text(text = artist, fontSize = artistTextSize, maxLines = 1)
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
