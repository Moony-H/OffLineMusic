package com.moony.common

import androidx.compose.runtime.compositionLocalOf
import androidx.media3.session.MediaBrowser

val LocalMediaBrowser = compositionLocalOf<MediaBrowser?> { error("No LocalMediaBrowser provided") }
