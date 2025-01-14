package com.moony.offlinemusic

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.media3.session.legacy.MediaSessionCompat
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.moony.common.LocalMediaBrowser
import com.moony.common.LocalMediaController
import com.moony.media_service.MediaService
import com.moony.offlinemusic.ui.theme.OffLineMusicTheme
import com.moony.music_player.MusicScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var mediaController by mutableStateOf<MediaController?>(null)
    private var mediaBrowser by mutableStateOf<MediaBrowser?>(null)

    private lateinit var mediaControllerFuture: ListenableFuture<MediaController>
    private lateinit var mediaBrowserFuture: ListenableFuture<MediaBrowser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

            }
        }
        //MediaService 토큰 받기
        val sessionToken = SessionToken(this, ComponentName(this, MediaService::class.java))
        //토큰으로 MediaController의 future 생성
        mediaControllerFuture = MediaController.Builder(this, sessionToken)
            .buildAsync()
        //토큰으로 MediaBrowser의 future 생성
        mediaBrowserFuture = MediaBrowser.Builder(this, sessionToken)
            .buildAsync()
        //MediaController가 준비되면 저장
        mediaControllerFuture.addListener({
            mediaController = mediaControllerFuture.get()
        }, MoreExecutors.directExecutor())
        //MediaBrowser가 준비되면 저장
        mediaBrowserFuture.addListener({
            mediaBrowser = mediaBrowserFuture.get()
        }, MoreExecutors.directExecutor())

        enableEdgeToEdge()
        setContent {
            OffLineMusicTheme {
                CompositionLocalProvider(
                    LocalMediaController.provides(mediaController),
                    LocalMediaBrowser.provides(mediaBrowser)
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MusicScreen(modifier = Modifier.padding(innerPadding))
                    }
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
