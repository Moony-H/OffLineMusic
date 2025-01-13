package com.moony.common

import androidx.compose.runtime.compositionLocalOf
import androidx.media3.session.MediaController


val LocalMediaController = compositionLocalOf<MediaController?> { error("No MediaSession provided") }

