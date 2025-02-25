package com.moony.domain.repository

import com.moony.domain.model.Music

interface MediaServiceRepository {
    fun play()
    fun addMusic(music: Music)
    fun addMusic(index: Int,music: Music)
    fun addMusics(musicList:List<Music>)
    fun removeMusic(index: Int)
    fun removeMusics(from: Int, to: Int)
    fun pause()
    fun next()
    fun previous()
    fun repeat()
    val playList:List<Music>?
}
