package com.moony.common

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bumptech.glide.Glide
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class MediaViewModel(private val mediaPlayer: MediaPlayer) : ViewModel(),
    MediaPlayer by mediaPlayer {
    abstract val musicPagingFlow: Flow<PagingData<Music>>
    abstract fun searchAndAddMusic(query: String)
    abstract val currentLyricsPartFlow: StateFlow<String>
    abstract val nextLyricsPartFlow: StateFlow<String>
    protected abstract val currentPositionStateFlow: StateFlow<Long>
    abstract val albumImage: StateFlow<Bitmap?>
    abstract val albumColors:StateFlow<List<Color>>
}
