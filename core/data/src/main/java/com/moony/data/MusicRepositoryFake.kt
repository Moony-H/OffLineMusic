package com.moony.data

import android.content.Context
import com.moony.data.dto.toMusic
import com.moony.domain.model.Music
import com.moony.domain.repository.MusicRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicRepositoryFake @Inject constructor(@ApplicationContext context: Context) :
    MusicRepository {
    private val dummyGenerator = DummyGenerator(context)

    override suspend fun getPlayList(): Result<List<Music>> = withContext(Dispatchers.IO) {
        delay(1000L)
        Result.success(dummyGenerator.getRandomMusicDTOList().map { it.toMusic() })
    }

    override suspend fun searchMusicByTitle(title: String): Result<Music> =
        withContext(Dispatchers.IO) {
            delay(1000L)
            val result = dummyGenerator.searchMusicByTitle(title)?.toMusic()
            result?.let { Result.success(it) } ?: let {
                Result.failure(IllegalStateException("Fail"))
            }
        }
}
