package com.moony.offlinemusic

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import com.moony.common.composable.SlideUpLayout
import com.moony.common.media.LocalMediaViewModel
import com.moony.music_player.MusicScreen


@Composable
fun MainScreen() {
    val snackBarHostState = remember { SnackbarHostState() }


    val context = LocalContext.current
    val viewModel = LocalMediaViewModel.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        SlideUpLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = innerPadding.calculateLeftPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateRightPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding()
                ),
            slideContent = {
                MusicScreen(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldTopPadding = innerPadding.calculateTopPadding()
                )
            },
            backgroundContent = {

            }
        )

    }
}
