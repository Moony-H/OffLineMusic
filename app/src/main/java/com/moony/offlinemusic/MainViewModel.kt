package com.moony.offlinemusic

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bumptech.glide.Glide
import com.moony.common.BitmapManager
import com.moony.common.MediaViewModel
import com.moony.data.paging.MusicPagingSource
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import com.moony.domain.repository.MusicRepository
import com.moony.domain.type.SnackBarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.moony.resource.R

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    mediaPlayer: MediaPlayer,
    private val musicRepository: MusicRepository,
) : MediaViewModel(mediaPlayer) {


    override val currentPositionStateFlow: StateFlow<Long> =
        currentPositionFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0L)

    private val _snackBarFlow = MutableSharedFlow<SnackBarEvent>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val snackBarFlow = _snackBarFlow.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val currentLyricsPartFlow: StateFlow<String> =
        currentMusicFlow.filterNotNull().flatMapLatest { music ->
            currentPositionStateFlow.map { position ->
                music.lyrics.getLyricsPartByCurrentMillis(position)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")


    @OptIn(ExperimentalCoroutinesApi::class)
    override val nextLyricsPartFlow: StateFlow<String> =
        currentMusicFlow.filterNotNull().flatMapLatest { music ->
            currentPositionStateFlow.map { position ->
                music.lyrics.getNextLyricsPartByCurrentMillis(
                    position
                ) ?: ""
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    @OptIn(ExperimentalCoroutinesApi::class)
    override val musicListPagingFlow: Flow<PagingData<Music>> =musicCountFlow.flatMapLatest {
        Pager(config = PagingConfig(
            pageSize = MusicPagingSource.ITEM_COUNT_PER_PAGE,
            enablePlaceholders = false,
        ), pagingSourceFactory = { MusicPagingSource(mediaPlayer) }).flow
    }

    override val albumImage = currentMusicFlow.filterNotNull().map { music ->
        Glide.with(context)
            .load(music.imageUrl)
            .error(R.drawable.no_image_placeholder)
            .submit().get().toBitmap()
    }.flowOn(Dispatchers.IO).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    override val albumColors: StateFlow<List<Color>> = albumImage.filterNotNull().map {
        BitmapManager.extractColorFromBitmap(it)
    }.flowOn(Dispatchers.IO).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )

    //test용
    init {
        if (musicCountFlow.value == 0) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = musicRepository.getPlayList()
                result.onSuccess {
                    withContext(Dispatchers.Main) { addMusics(it) }
                }.onFailure {
                    postSnackBarEvent(SnackBarEvent.Message("네트워크 오류가 발생하였습니다", null))
                }
            }
        }

    }

    override fun searchAndAddMusic(query: String) {
        viewModelScope.launch {
            val result = musicRepository.searchMusicByTitle(query)
            result.onSuccess {
                addMusic(it)
            }.onFailure {
                postSnackBarEvent(SnackBarEvent.Message("등록되지 않은 음악입니다.", null))
            }
        }
    }

    fun postSnackBarEvent(even: SnackBarEvent) {
        viewModelScope.launch {
            _snackBarFlow.emit(even)
        }
    }


}
