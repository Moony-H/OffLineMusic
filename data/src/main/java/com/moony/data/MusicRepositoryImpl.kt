package com.moony.data

import android.content.Context
import com.moony.data.dto.toMusic
import com.moony.domain.model.Music
import com.moony.domain.repository.MusicRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MusicRepositoryFake @Inject constructor(@ApplicationContext context: Context) :
    MusicRepository {
    private val dummyGenerator = DummyGenerator(context)

    override suspend fun getPlayList(): List<Music> {
        return dummyGenerator.getRandomMusicDTOList().map { it.toMusic() }
    }

    override suspend fun searchMusicByTitle(title: String): Music? {
        return dummyGenerator.searchMusicByTitle(title)?.toMusic()
    }
}
