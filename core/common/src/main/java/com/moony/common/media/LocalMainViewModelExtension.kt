package com.moony.common.media

import androidx.compose.runtime.compositionLocalOf
import com.moony.common.MediaViewModel


val LocalMediaViewModel = compositionLocalOf<MediaViewModel> { error("No LocalMediaViewModel provided") }
