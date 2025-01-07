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
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView


@OptIn(UnstableApi::class)
@Composable
fun MusicPlayer(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    val musicUri by remember { mutableStateOf("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3") }
    //val videoUri by remember { mutableStateOf("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") }
    player.setMediaItem(MediaItem.fromUri(musicUri))
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            PlayerView(context)
        }) {
        it.player = player
    }

    player.playWhenReady = true
    player.prepare()
    player.play()
}
