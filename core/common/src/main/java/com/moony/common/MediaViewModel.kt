package com.moony.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class MediaViewModel(private val mediaPlayer: MediaPlayer) : ViewModel(),
    MediaPlayer by mediaPlayer {
    abstract val musicPagingFlow: Flow<PagingData<Music>>
    abstract fun searchAndAddMusic(query: String)
    abstract val currentLyricsPartFlow:StateFlow<String>
    abstract val prevLyricsPartFlow:StateFlow<String>

}
