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

    private val mediaLibrarySessionCallback = object : MediaLibrarySession.Callback {}

    @Inject
    lateinit var musicRepository: MusicRepository

    @Inject
    lateinit var mediaLibrarySession: MediaLibrarySession

    override fun onCreate() {
        super.onCreate()

    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo) =
        mediaLibrarySession

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = mediaLibrarySession.player
        if (!player.playWhenReady
            || player.mediaItemCount == 0
            || (player.playbackState == Player.STATE_ENDED
                    && player.repeatMode == Player.REPEAT_MODE_OFF) //곡이 끝났고, 반복 재생 모드가 아닐 때
        ) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        mediaLibrarySession.run {
            player.release()
            release()
        }
        super.onDestroy()

    }


}
