package com.moony.domain.repository

import com.moony.domain.model.Music

interface MusicRepository {
    suspend fun getPlayList(): List<Music>
    suspend fun searchMusicByTitle(title: String): Music?
}
