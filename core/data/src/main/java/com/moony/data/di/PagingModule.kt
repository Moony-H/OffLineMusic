package com.moony.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.moony.data.paging.MusicPagingSource
import com.moony.domain.manager.MediaPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {

    @Provides
    fun providesPager(mediaPlayer: MediaPlayer) = Pager(config = PagingConfig(
        pageSize = MusicPagingSource.ITEM_COUNT_PER_PAGE,
        enablePlaceholders = false,
    ), pagingSourceFactory = { MusicPagingSource(mediaPlayer) })
}
