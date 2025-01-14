package com.moony.media_service.di

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaLibraryService.MediaLibrarySession
import com.moony.media_service.MediaLibrarySessionCallback
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaServiceProvidesModule {
    @Provides
    @Singleton
    fun providesExoPlayer(@ApplicationContext context: Context) = ExoPlayer.Builder(context).build()

    @OptIn(UnstableApi::class)
    @Provides
    @Singleton
    fun providesMediaLibrarySession(@ApplicationContext context: Context, player: Player) =
        MediaLibrarySession.Builder(
            context,
            player,
            MediaLibrarySessionCallback()
        ).build()
}
