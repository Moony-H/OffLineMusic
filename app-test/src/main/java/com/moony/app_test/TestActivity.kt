package com.moony.app_test

import android.content.ComponentName
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.moony.common.composable.GradientBackground
import com.moony.common.composable.GradientBox
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
                        val direction = LocalLayoutDirection.current
                        Box(modifier = Modifier.padding(innerPadding)) {
                            val a = rememberInfiniteTransition()
                            val b = a.animateFloat(
                                initialValue = -1f,
                                targetValue = 1f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1000),
                                    repeatMode = RepeatMode.Reverse
                                )
                            )
                        }
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

