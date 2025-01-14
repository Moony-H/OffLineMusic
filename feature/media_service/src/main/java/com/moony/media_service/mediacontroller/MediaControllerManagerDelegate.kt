package com.moony.media_service.mediacontroller

import androidx.activity.ComponentActivity
import androidx.media3.session.MediaController
import com.moony.common.media.toMediaItem
import com.moony.domain.model.Music

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





