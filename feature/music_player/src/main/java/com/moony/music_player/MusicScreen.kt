package com.moony.music_player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moony.music_player.component.MusicPlayer

@Composable
fun MusicScreen(modifier: Modifier = Modifier) {
    MusicPlayer(modifier = modifier)
}
