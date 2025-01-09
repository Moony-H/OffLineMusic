package com.moony.media_service

import android.content.Intent
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession

class MediaService : MediaLibraryService() {

    private var mediaLibrarySession: MediaLibrarySession? = null
    private val mediaLibrarySessionCallback = object : MediaLibrarySession.Callback {    }

    override fun onCreate() {
        super.onCreate()
        mediaLibrarySession = MediaLibrarySession.Builder(
            this,
            ExoPlayer.Builder(this).build(),
            mediaLibrarySessionCallback
        ).build()

    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo) =
        mediaLibrarySession

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = mediaLibrarySession?.player!!
        if (!player.playWhenReady
            || player.mediaItemCount == 0
            || (player.playbackState == Player.STATE_ENDED
                    && player.repeatMode == Player.REPEAT_MODE_OFF) //곡이 끝났고, 반복 재생 모드가 아닐 때
        ) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        mediaLibrarySession?.run {
            player.release()
            release()
            mediaLibrarySession = null
        }
        super.onDestroy()

    }



}
