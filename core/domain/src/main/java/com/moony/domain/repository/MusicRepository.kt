package com.moony.domain.repository

import com.moony.domain.model.Music

interface MusicRepository {
    suspend fun getPlayList(): Result<List<Music>>
    suspend fun searchMusicByTitle(title: String): Result<Music>
}
