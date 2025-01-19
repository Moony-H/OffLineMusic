package com.moony.offlinemusic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.LayoutDirection
import com.moony.common.composable.SlideUpLayout
import com.moony.common.media.LocalMediaViewModel
import com.moony.music_player.MusicScreen
import com.moony.playlist.PlaylistScreen
import com.moony.resource.BackgroundBlack
import com.moony.resource.R


@Composable
fun MainScreen() {
    val snackBarHostState = remember { SnackbarHostState() }


    var dragProcess by remember { mutableFloatStateOf(0f) }
    val bottomSlideOffset = dimensionResource(R.dimen.height_bottom_mini_player)
    val direction = LocalLayoutDirection.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }
    ) { innerPadding ->
        val startPadding = innerPadding.calculateStartPadding(direction)
        val endPadding = innerPadding.calculateEndPadding(direction)
        val topPadding = innerPadding.calculateTopPadding()
        val bottomPadding = innerPadding.calculateBottomPadding()
        SlideUpLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = startPadding,
                    end = endPadding,
                    bottom = bottomPadding
                ),
            onDragProgressChanged = { dragProcess = it },
            bottomOffsetDp = bottomSlideOffset,
            slideContent = {
                MusicScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundBlack),
                    scaffoldTopPadding = topPadding,
                    alpha = dragProcess
                )
            },
            backgroundContent = {
                PlaylistScreen(
                    modifier = Modifier
                        .padding(
                            bottom = bottomSlideOffset,
                            start = startPadding,
                            end = endPadding
                        )
                        .fillMaxSize(),
                    topPadding = topPadding
                )
            }
        )

    }
}
