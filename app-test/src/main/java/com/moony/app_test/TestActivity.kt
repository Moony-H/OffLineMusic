package com.moony.app_test

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.moony.common.media.LocalMediaViewModel
import com.moony.designsystem.OffLineMusicTheme
import com.moony.media_service.MediaService
import com.moony.playlist.PlaylistScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestActivity : ComponentActivity() {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService()
        enableEdgeToEdge()
        setContent {
            OffLineMusicTheme {
                CompositionLocalProvider(LocalMediaViewModel.provides(viewModel)) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        PlaylistScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            0.dp
                        )
                    }
                }

            }
        }
    }

    private fun startService() {
        val sessionToken =
            SessionToken(applicationContext, ComponentName(this, MediaService::class.java))
        MediaController.Builder(applicationContext, sessionToken).buildAsync()
    }
}

