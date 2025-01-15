package com.moony.music_player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moony.common.media.LocalMediaViewModel

@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
) {
    val mediaViewModel = LocalMediaViewModel.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}
