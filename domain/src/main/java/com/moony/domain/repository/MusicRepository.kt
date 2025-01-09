package com.moony.domain.repository

import com.moony.domain.model.Music

interface MusicRepository {
    suspend fun searchMusicByName(name:String):List<Music>
}
