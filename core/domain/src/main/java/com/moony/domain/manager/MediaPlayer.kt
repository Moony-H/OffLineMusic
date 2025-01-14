package com.moony.domain.manager

import com.moony.domain.model.Music
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MediaPlayer {
    val isPlayingFlow: StateFlow<Boolean>
    val currentMusicTimeLinePositionFlow: StateFlow<Long>
    val durationFlow: StateFlow<Long>
    val currentMusicFlow: StateFlow<Music?>
    fun play()
    fun pause()
    fun next()
    fun previous()
    fun seekTo(positionMillis: Long)
    fun addMusic(music: Music)
    fun addMusic(index: Int, music: Music)
    fun addMusics(musics: List<Music>)
    fun addMusics(index: Int, musics: List<Music>)
}
