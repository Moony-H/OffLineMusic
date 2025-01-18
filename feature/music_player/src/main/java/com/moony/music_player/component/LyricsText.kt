package com.moony.music_player.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moony.resource.LyricsDisableGray
import com.moony.resource.LyricsEnableGray

@Composable
fun LyricsText(current: String, next: String) {
    Column {
        Text(current, color = LyricsEnableGray)
        Spacer(Modifier.height(2.dp))
        Text(next, color = LyricsDisableGray)
    }
}
