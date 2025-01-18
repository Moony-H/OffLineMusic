package com.moony.music_player.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.moony.domain.type.RepeatMode
import com.moony.resource.R


fun RepeatMode.getIconId() = when (this) {
    RepeatMode.NONE -> R.drawable.icon_repeat_none
    RepeatMode.ONE -> R.drawable.icon_repeat_one
    RepeatMode.ALL -> R.drawable.icon_repeat_all
}

fun RepeatMode.getDescriptionId()= when (this) {
    RepeatMode.NONE -> R.string.content_repeat_none
    RepeatMode.ONE -> R.string.content_repeat_one
    RepeatMode.ALL -> R.string.content_repeat_all
}

