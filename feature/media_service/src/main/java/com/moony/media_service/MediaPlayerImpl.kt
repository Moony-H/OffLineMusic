package com.moony.media_service

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import com.moony.domain.type.PlayerError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import javax.inject.Inject

class MediaPlayerImpl @Inject constructor(
    private val player: Player
) : MediaPlayer {
    override val isPlayingFlow = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val currentPositionFlow =
        isPlayingFlow.filter { it }.flatMapLatest {
            flow {
                while (isPlayingFlow.value) {
                    delay(250L)
                    emit(player.currentPosition)
                }
            }
        }

    override val durationFlow = MutableStateFlow(0L)
    override val currentMusicFlow = MutableStateFlow<Music?>(null)
    override val errorFlow = MutableSharedFlow<PlayerError>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val nowMusicItemIndexFlow = MutableStateFlow(player.currentMediaItemIndex)
    override val musicCountFlow = MutableStateFlow(0)

    init {
        player.addListener(object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                currentMusicFlow.value = mediaItem?.toMusic()
                durationFlow.value = player.duration
            }


            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                isPlayingFlow.value = isPlaying
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                errorFlow.tryEmit(error.toPlayerError())
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
            }

        })
    }

    override fun play() {
        if (player.playbackState == Player.STATE_IDLE)
            player.prepare()
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun next() {
        player.seekToNext()
    }

    override fun previous() {
        player.seekToPrevious()
    }

    override fun seekTo(positionMillis: Long) {
        player.seekTo(positionMillis)
    }

    override fun seekMusicTo(index: Int) {
        player.seekTo(index, 0L)
    }

    override fun addMusic(music: Music) {
        player.addMediaItem(music.toMediaItem())
        updateMusicCount()
    }

    override fun addMusic(index: Int, music: Music) {
        player.addMediaItem(index, music.toMediaItem())
        updateMusicCount()

    }

    override fun addMusics(musics: List<Music>) {
        player.addMediaItems(musics.map { it.toMediaItem() })
        updateMusicCount()

    }

    override fun addMusics(index: Int, musics: List<Music>) {
        player.addMediaItems(index, musics.map { it.toMediaItem() })
        updateMusicCount()
    }

    override fun getMusic(index: Int): Music? {
        if (player.mediaItemCount <= index) return null
        return player.getMediaItemAt(index).toMusic()
    }

    private fun updateMusicCount() {
        musicCountFlow.value = player.mediaItemCount
    }


}
