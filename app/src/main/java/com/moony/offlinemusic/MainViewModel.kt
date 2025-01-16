package com.moony.offlinemusic

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import com.moony.common.MediaViewModel
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import com.moony.domain.repository.MusicRepository
import com.moony.domain.type.SnackBarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mediaPlayer: MediaPlayer,
    private val musicRepository: MusicRepository,
    private val musicPager: Pager<Int, Music>
) : MediaViewModel(mediaPlayer) {

    private val _snackBarFlow = MutableSharedFlow<SnackBarEvent>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val snackBarFlow = _snackBarFlow.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val currentLyricsPartFlow: StateFlow<String> =
        currentMusicFlow.filterNotNull().flatMapLatest { music ->
            currentPositionFlow.filterNotNull()
                .map { position -> music.lyrics.getLyricsPartByCurrentMillis(position) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")


    @OptIn(ExperimentalCoroutinesApi::class)
    override val nextLyricsPartFlow: StateFlow<String> =
        currentMusicFlow.filterNotNull().flatMapLatest { music ->
            currentPositionFlow.map { position ->
                music.lyrics.getNextLyricsPartByCurrentMillis(
                    position
                ) ?: ""
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "")

    override val musicPagingFlow: Flow<PagingData<Music>> = musicPager.flow

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

    fun postSnackBarEvent(even: SnackBarEvent) {
        viewModelScope.launch {
            _snackBarFlow.emit(even)
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
}
