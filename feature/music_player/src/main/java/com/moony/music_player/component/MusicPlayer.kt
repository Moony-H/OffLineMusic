package com.moony.music_player.component

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView
import com.moony.common.LocalMediaController


@OptIn(UnstableApi::class)
@Composable
fun MusicPlayer(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mediaController= LocalMediaController.current
    val player = remember { ExoPlayer.Builder(context).build() }
}
