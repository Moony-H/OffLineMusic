package com.moony.domain.manager

import com.moony.domain.model.Music
import com.moony.domain.type.PlayerError
import com.moony.domain.type.RepeatMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MediaPlayer {
    val isPlayingFlow: StateFlow<Boolean>
    val currentPositionFlow: Flow<Long>
    val durationFlow: StateFlow<Long>
    val currentMusicFlow: StateFlow<Music?>
    val musicCountFlow: StateFlow<Int>
    val errorFlow: SharedFlow<PlayerError>
    val currentMusicIndexFlow: StateFlow<Int>
    val repeatModeFlow: StateFlow<RepeatMode>
    val isShuffleFlow: StateFlow<Boolean>
    fun play()
    fun pause()
    fun next()
    fun previous()
    fun seekTo(positionMillis: Long)
    fun seekMusicByIndex(index: Int)
    fun addMusic(music: Music)
    fun addMusic(index: Int, music: Music)
    fun addMusics(musics: List<Music>)
    fun addMusics(index: Int, musics: List<Music>)
    fun getMusic(index: Int): Music?
    fun setRepeatMode(repeatMode: RepeatMode)
    fun setShuffle(isShuffle: Boolean)
}
