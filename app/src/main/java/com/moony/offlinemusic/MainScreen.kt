package com.moony.offlinemusic

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.LayoutDirection
import com.moony.common.composable.SlideUpLayout
import com.moony.common.media.LocalMediaViewModel
import com.moony.music_player.MusicScreen
import com.moony.resource.R


@Composable
fun MainScreen() {
    val snackBarHostState = remember { SnackbarHostState() }


    val context = LocalContext.current
    val viewModel = LocalMediaViewModel.current
    var dragProcess by remember { mutableFloatStateOf(0f) }

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
            onDragProgressChanged = { dragProcess = it },
            bottomOffsetDp = dimensionResource(R.dimen.bottom_mini_player_height),
            slideContent = {
                MusicScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    scaffoldTopPadding = innerPadding.calculateTopPadding(),
                    alpha = dragProcess
                )
            },
            backgroundContent = {

            }
        )

    }
}
