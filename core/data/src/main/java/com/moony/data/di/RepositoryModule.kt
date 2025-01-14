package com.moony.data.di

import com.moony.data.MusicRepositoryFake
import com.moony.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMusicRepositoryFake(musicRepositoryFake: MusicRepositoryFake): MusicRepository
}
