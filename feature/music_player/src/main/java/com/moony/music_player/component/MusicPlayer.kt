package com.moony.music_player.component

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.moony.common.media.LocalMediaController


@OptIn(UnstableApi::class)
@Composable
fun MusicPlayer(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mediaController= LocalMediaController.current
    val player = remember { ExoPlayer.Builder(context).build() }
}
