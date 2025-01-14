package com.moony.media_service.di

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaLibraryService.MediaLibrarySession
import androidx.media3.session.MediaSession
import com.moony.domain.manager.MediaPlayer
import com.moony.media_service.MediaPlayerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaServiceBindsModule {
    @Binds
    @Singleton
    abstract fun providesMediaPlayer(mediaPlayerImpl: MediaPlayerImpl): MediaPlayer

    @Binds
    @Singleton
    abstract fun bindsPlayer(exoPlayer: ExoPlayer): Player

    @Binds
    @Singleton
    abstract fun bindsMediaSession(mediaLibrarySession: MediaLibrarySession): MediaSession
}
