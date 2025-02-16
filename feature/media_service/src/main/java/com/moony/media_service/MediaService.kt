package com.moony.media_service

import android.content.Intent
import android.util.Log
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession
import androidx.media3.session.legacy.PlaybackStateCompat
import com.moony.domain.repository.MusicRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MediaService : MediaLibraryService() {

    @Inject
    lateinit var musicRepository: MusicRepository

    @Inject
    lateinit var mediaLibrarySession: MediaLibrarySession


    override fun onCreate() {
        super.onCreate()
        Log.e("test", "create")
        Log.e("test","librarysession: ${System.identityHashCode(mediaLibrarySession)}")
        Log.e("test","app context: ${System.identityHashCode(application)}")
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaLibrarySession {
        Log.e("test","mediaLibraryService on get session")
        return mediaLibrarySession
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        mediaLibrarySession.run {
            release()
        }
        Log.e("test", "service destroyed")
        super.onDestroy()

    }


}
