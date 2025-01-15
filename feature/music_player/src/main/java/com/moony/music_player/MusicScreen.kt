package com.moony.music_player

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.moony.common.media.LocalMediaViewModel

@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
) {
    val mediaViewModel = LocalMediaViewModel.current
    LaunchedEffect(Unit) {
        mediaViewModel.currentPositionFlow.collect {
        }
    }

    LaunchedEffect(Unit){
        mediaViewModel.musicCountFlow.collect{
        }
    }

    var buttonState = remember { true }
    Box(modifier = modifier.fillMaxSize()) {

        Button(onClick = {
            if (buttonState) {
                mediaViewModel.play()
                buttonState = false
            } else {
                mediaViewModel.pause()
                buttonState = true
            }
        }) {
            Text(text = "play")
        }
    }
}
