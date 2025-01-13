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
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.moony.common.LocalMediaBrowser
import com.moony.common.LocalMediaController
import com.moony.media_service.MediaService
import com.moony.offlinemusic.ui.theme.OffLineMusicTheme
import com.moony.music_player.MusicScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var mediaController by mutableStateOf<MediaController?>(null)
    private var mediaBrowser by mutableStateOf<MediaBrowser?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sessionToken = SessionToken(this, ComponentName(this, MediaService::class.java))
        val controllerFuture = MediaController.Builder(this, sessionToken)
            .buildAsync()
        val mediaBrowserFuture = MediaBrowser.Builder(this, sessionToken)
            .buildAsync()
        controllerFuture.addListener({
            mediaController = controllerFuture.get()
        }, MoreExecutors.directExecutor())
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

    override fun onStart() {
        super.onStart()


//        controllerFuture.addListener({
//            val a = controllerFuture.get()
//            a.play()
//        }, MoreExecutors.directExecutor())

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OffLineMusicTheme {
        Greeting("Android")
    }
}
