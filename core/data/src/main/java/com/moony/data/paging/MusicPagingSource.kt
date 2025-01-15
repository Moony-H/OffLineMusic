package com.moony.data.paging

import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import javax.inject.Inject

class MusicPagingSource @Inject constructor(private val mediaPlayer: MediaPlayer) :
    CommonPagingSource<Music>() {
    override suspend fun providePage(page: Int): Result<List<Music>> {
        val result = Array(ITEM_COUNT_PER_PAGE) {
            val index = it + page * ITEM_COUNT_PER_PAGE
            mediaPlayer.getMusic(index) ?: return Result.success(listOf())
        }.toList()
        return Result.success(result)
    }

    companion object {
        const val ITEM_COUNT_PER_PAGE = 5
    }


}
