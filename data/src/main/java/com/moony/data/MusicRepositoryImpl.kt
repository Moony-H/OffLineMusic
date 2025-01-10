package com.moony.data

import com.moony.domain.model.Music
import com.moony.domain.repository.MusicRepository

class MusicRepositoryImpl:MusicRepository {
    override suspend fun searchMusicByName(name: String): List<Music> {
        return listOf()
    }
}
