package com.moony.common

import androidx.lifecycle.ViewModel
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import kotlinx.coroutines.flow.StateFlow

abstract class MediaViewModel : ViewModel(), MediaPlayer {
    abstract fun searchAndAddMusic(query: String)
}
