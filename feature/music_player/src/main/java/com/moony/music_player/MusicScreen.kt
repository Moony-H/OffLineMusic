package com.moony.music_player

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.media3.common.MediaItem
import com.moony.common.LocalMediaBrowser
import com.moony.common.LocalMediaController
import com.moony.music_player.component.MusicPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MusicScreen(modifier: Modifier = Modifier) {
    val localMediaController = LocalMediaController.current
    val localMediaBrowser = LocalMediaBrowser.current
    localMediaController?.addMediaItem(
        MediaItem.Builder().setMediaId("1")
            .setUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3").build()
    )
    localMediaController?.play()

    MusicPlayer(modifier = modifier)
}
