package com.moony.playlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moony.common.media.LocalMediaViewModel


@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {
    val viewModel = LocalMediaViewModel.current
    val playlist = viewModel.musicPagingFlow
}
