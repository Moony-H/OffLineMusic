package com.moony.common.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaController
import com.moony.domain.model.Music
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
abstract class MediaViewModel : ViewModel() {
    protected abstract val mediaControllerFlow: MutableStateFlow<MediaController?>
    protected abstract val mediaBrowserFlow: MutableStateFlow<MediaBrowser?>

    //abstract value를 바로 mediaStateFlow로 변환하면, 클래스가 초기화 되기 전에 사용할 수 있음. 예상치 못한 버그가 발생할 수 있다.
    //따라서 lazy로 사용할 때 초기화하자.
    val musicPlayBackPositionFlow: StateFlow<Long> by lazy {
        mediaControllerFlow.filterNotNull().flatMapLatest { controller ->
            callbackFlow {
                controller.addListener(object : Player.Listener {
                    override fun onTimelineChanged(timeline: Timeline, reason: Int) {
                        super.onTimelineChanged(timeline, reason)
                        trySend(controller.currentPosition)
                    }
                })
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L), 0L)
    }

    val musicDurationFlow:StateFlow<Long> by lazy {
        mediaControllerFlow.filterNotNull().flatMapLatest { controller->
            callbackFlow {
                controller.addListener(object :Player.Listener{
                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        super.onMediaItemTransition(mediaItem, reason)
                        trySend(controller.duration)
                    }
                })
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000L),0L)
    }

    val musicInfoFlow:StateFlow<Music?> by lazy {
        mediaControllerFlow.filterNotNull().flatMapLatest { controller ->
            callbackFlow {
                controller.addListener(object : Player.Listener {
                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        super.onMediaItemTransition(mediaItem, reason)
                        trySend(mediaItem?.toMusic())
                    }
                })
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
    }


    fun play() {
        mediaControllerFlow.value?.play()
    }

    fun pause() {
        mediaControllerFlow.value?.pause()
    }

    fun next() {
        mediaControllerFlow.value?.seekToNext()
    }

    fun previous() {
        mediaControllerFlow.value?.seekToPrevious()
    }

    fun addMusic(music: Music) {
        mediaControllerFlow.value?.addMediaItem(music.toMediaItem())
    }

    fun addMusic(index: Int, music: Music) {
        mediaControllerFlow.value?.addMediaItem(index, music.toMediaItem())
    }

    fun addMusics(musicList: List<Music>) {
        mediaControllerFlow.value?.addMediaItems(musicList.map { it.toMediaItem() })
    }

    fun removeMusic(index: Int) {
        mediaControllerFlow.value?.removeMediaItem(index)
    }

    fun removeMusics(from: Int, to: Int) {
        mediaControllerFlow.value?.removeMediaItems(from, to)
    }

    fun repeat() {
        mediaControllerFlow.value?.repeatMode
    }

}
