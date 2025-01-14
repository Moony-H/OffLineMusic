package com.moony.offlinemusic

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.moony.common.media.LocalMediaViewModel
import com.moony.common.showSnackBarWithEvent
import com.moony.music_player.MusicScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        MusicScreen(modifier = Modifier.padding(innerPadding))
    }
    LaunchedEffect(Unit) {
//        mainViewModel.snackBarEventFlow.collect {
//            snackBarHostState.showSnackBarWithEvent(it)
//        }
    }
}
