package com.moony.music_player.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun MillisToTimeText(modifier: Modifier = Modifier, timeMillis: Long, size: TextUnit) {
    val minute = timeMillis / 60000L
    val second = (timeMillis % 60000L) / 1000L
    Text(
        modifier = Modifier,
        text = "$minute:${second.toString().padStart(2, '0')}",
        fontSize = size
    )
}
