package com.moony.media_service.mediacontroller

import android.content.ComponentName
import androidx.activity.ComponentActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.moony.domain.model.Music
import com.moony.media_service.MediaService
import com.moony.media_service.toStringOrEmpty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

internal class MediaControllerManagerDelegate(activity: ComponentActivity) :
    MediaControllerManager {
    fun release() {
        collectJob?.cancel()
        collectJob = null
        mediaController.value?.release()
        mediaController.value = null
        MediaController.releaseFuture(mediaControllerFuture)
    }

    override fun play() {
        mediaController.value?.play()
    }


    override fun pause() {
        mediaController.value?.pause()
    }

    override fun next() {
        mediaController.value?.seekToNext()
    }

    override fun previous() {
        mediaController.value?.seekToPrevious()
    }

    override fun addMusic(music: Music) {
        mediaController.value?.addMediaItem(music.toMediaItem())
    }

    override fun addMusic(index: Int, music: Music) {
        mediaController.value?.addMediaItem(index, music.toMediaItem())
    }

    override fun addMusics(musicList: List<Music>) {
        mediaController.value?.addMediaItems(musicList.map { it.toMediaItem() })
    }

    override fun removeMusic(index: Int) {
        mediaController.value?.removeMediaItem(index)
    }

    override fun removeMusics(from: Int, to: Int) {
        mediaController.value?.removeMediaItems(from, to)
    }

    override fun repeat() {
        mediaController.value?.repeatMode
    }



}





