package com.moony.offlinemusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
import com.moony.common.media.LocalMediaBrowser
import com.moony.common.media.LocalMediaController
import com.moony.common.media.LocalMediaViewModel
import com.moony.offlinemusic.ui.theme.OffLineMusicTheme
import com.moony.music_player.MusicScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var mediaController by mutableStateOf<MediaController?>(null)
    private var mediaBrowser by mutableStateOf<MediaBrowser?>(null)

    private val viewModel: MainViewModel by viewModels()

    private lateinit var mediaControllerFuture: ListenableFuture<MediaController>
    private lateinit var mediaBrowserFuture: ListenableFuture<MediaBrowser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OffLineMusicTheme {
                CompositionLocalProvider(
                    LocalMediaController.provides(mediaController),
                    LocalMediaBrowser.provides(mediaBrowser),
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaController?.release()
        mediaBrowser?.release()
        MediaController.releaseFuture(mediaControllerFuture)
        MediaBrowser.releaseFuture(mediaBrowserFuture)
    }
}
