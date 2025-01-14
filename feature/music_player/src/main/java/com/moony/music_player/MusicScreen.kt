package com.moony.music_player

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moony.common.media.LocalMediaViewModel

@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
) {
    val mediaViewModel = LocalMediaViewModel.current
    Box(modifier = modifier.fillMaxSize()) {
        Button(onClick = {
            mediaViewModel.play()
            Log.e("test", "musicCount: ${mediaViewModel.musicCount}")
        }) {
            Text(text = "play")
        }
    }
}
