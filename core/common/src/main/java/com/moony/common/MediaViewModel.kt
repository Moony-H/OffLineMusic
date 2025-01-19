package com.moony.common

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class MediaViewModel(private val mediaPlayer: MediaPlayer) : ViewModel(),
    MediaPlayer by mediaPlayer {
    abstract val musicListPagingFlow: Flow<PagingData<Music>>
    abstract fun searchAndAddMusic(query: String)
    abstract val currentLyricsPartFlow: StateFlow<String>
    abstract val nextLyricsPartFlow: StateFlow<String>
    protected abstract val currentPositionStateFlow: StateFlow<Long>
    abstract val albumImage: StateFlow<Bitmap?>
    abstract val albumColors:StateFlow<List<Color>>
}
