package com.moony.common.media

import androidx.compose.runtime.compositionLocalOf
import androidx.media3.session.MediaController


val LocalMediaController = compositionLocalOf<MediaController?> { error("No MediaSession provided") }

