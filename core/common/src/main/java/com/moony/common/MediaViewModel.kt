package com.moony.common

import androidx.lifecycle.ViewModel
import com.moony.domain.model.Music
import kotlinx.coroutines.flow.StateFlow

abstract class MediaViewModel : ViewModel() {
    abstract val isPlaying: StateFlow<Boolean>
    abstract val currentMusic: StateFlow<Music?>
    abstract val duration: StateFlow<Long>
    abstract val currentPosition: StateFlow<Long>
}
