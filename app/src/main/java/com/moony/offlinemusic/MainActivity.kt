package com.moony.offlinemusic

import android.content.ComponentName
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.moony.common.media.LocalMediaViewModel
import com.moony.media_service.MediaService
import com.moony.designsystem.OffLineMusicTheme
import com.moony.resource.BackgroundBlack
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService()
        enableEdgeToEdge()
        setContent {
            OffLineMusicTheme {
                CompositionLocalProvider(
                    LocalMediaViewModel.provides(viewModel)
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun startService() {
        val sessionToken =
            SessionToken(applicationContext, ComponentName(this, MediaService::class.java))
        MediaController.Builder(applicationContext, sessionToken).buildAsync()
    }

    private fun setSystemBars(isDarkMode: Boolean) {
        val systemBarStyle = if (!isDarkMode) {
            SystemBarStyle.light(
                BackgroundBlack.toArgb(),
                Color.White.toArgb()
            )
        } else {
            SystemBarStyle.dark(
                BackgroundBlack.toArgb()
            )
        }
        enableEdgeToEdge(statusBarStyle = systemBarStyle, navigationBarStyle = systemBarStyle)
    }

    private fun isDarkModeEnabled(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
}
