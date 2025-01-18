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
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.moony.resource.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MusicTitleAndArtist(title: String, artist: String, modifier: Modifier = Modifier) {
    val titleSize = dimensionResource(R.dimen.text_music_title_size).value.sp
    val artistTextSize = dimensionResource(R.dimen.text_music_artist_size).value.sp
    Column(modifier = modifier) {
        AnimatedContent(targetState = title, transitionSpec = { addAnimation().using(SizeTransform(clip = false)) },
            label = ""
        ) { target->
            Text(text = target, fontSize = titleSize, maxLines = 1)
        }
        Text(text = artist, fontSize = artistTextSize, maxLines = 1)
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 800): ContentTransform {
    return (slideInHorizontally(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    )).togetherWith(slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    ))
}
