package com.moony.offlinemusic

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.moony.common.BlurManager
import com.moony.common.SlideUpLayout
import com.moony.common.media.LocalMediaViewModel
import com.moony.music_player.MusicScreen
import com.moony.music_player.component.TopBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun MainScreen() {
    val snackBarHostState = remember { SnackbarHostState() }


    val context = LocalContext.current
    val viewModel = LocalMediaViewModel.current
    LaunchedEffect(Unit) {

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        SlideUpLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            slideContent = {
                MusicScreen(modifier = Modifier.fillMaxSize())
            },
            backgroundContent = {

            }
        )

    }
}
