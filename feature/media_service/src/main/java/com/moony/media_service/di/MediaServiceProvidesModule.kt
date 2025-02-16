package com.moony.media_service.di

import android.content.Context
import android.util.Log
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
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaServiceProvidesModule {
    @Provides
    @Singleton
    fun providesExoPlayer(@ApplicationContext context: Context): Player {
        Log.e("test", "providesExoPlayer")
        return ExoPlayer.Builder(context).build()
    }


}

@Module
@InstallIn(ServiceComponent::class)
object MediaLibrarySessionProvidesModule {
    @OptIn(UnstableApi::class)
    @Provides
    @ServiceScoped
    fun providesMediaLibrarySession(@ApplicationContext context: Context, player: Player) =
        MediaLibrarySession.Builder(
            context,
            player,
            MediaLibrarySessionCallback()
        ).build()
}
