package com.moony.media_service

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import com.moony.domain.type.PlayerError
import com.moony.domain.type.RepeatMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaPlayerImpl @Inject constructor(
    private val player: Player
) : MediaPlayer {
    override val isPlayingFlow = MutableStateFlow(false)
    override val currentMusicFlow = MutableStateFlow<Music?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val currentPositionFlow = currentMusicFlow
        .combine(isPlayingFlow) { currentMusic, isPlaying ->
            channelFlow {
                while (isPlaying && coroutineContext.isActive) {
                    delay(250L)
                    val position = withContext(Dispatchers.Main) { player.currentPosition }
                    send(position)
                }
            }
        }.flattenMerge().flowOn(Dispatchers.IO) //빠르게 변경되면 이전 flow가 취소되지 않은 상태에서 두 flow가 병렬로 실행될 수 있음.
    //그래서 flattenMerge로 병합함.

    override val durationFlow = MutableStateFlow(0L)
    override val errorFlow = MutableSharedFlow<PlayerError>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val currentMusicIndexFlow = MutableStateFlow(player.currentMediaItemIndex)
    override val musicCountFlow = MutableStateFlow(0)
    override val repeatModeFlow = MutableStateFlow(RepeatMode.NONE)
    override val isShuffleFlow = MutableStateFlow(false)

    init {
        player.addListener(object : Player.Listener {

            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                currentMusicFlow.value = mediaItem?.toMusic()
                currentMusicIndexFlow.value = player.currentMediaItemIndex
            }


            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                isPlayingFlow.value = isPlaying
                durationFlow.value = player.duration
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                errorFlow.tryEmit(error.toPlayerError())
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                super.onRepeatModeChanged(repeatMode)
                repeatModeFlow.value = getRepeatModeFromMedia(repeatMode)
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                super.onShuffleModeEnabledChanged(shuffleModeEnabled)
                isShuffleFlow.value = shuffleModeEnabled
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
        if (player.currentMediaItemIndex == player.mediaItemCount - 1) {
            player.seekTo(0, 0L)
            return
        }
        player.seekToNext()
    }

    override fun previous() {
        if (player.currentMediaItemIndex == 0) {
            player.seekTo(player.mediaItemCount - 1, 0L)
            return
        }
        player.seekToPrevious()
    }

    override fun seekTo(positionMillis: Long) {
        player.seekTo(positionMillis)
    }

    override fun seekMusicByIndex(index: Int) {
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

    override fun setRepeatMode(repeatMode: RepeatMode) {
        player.repeatMode = repeatMode.toMediaRepeatModeInt()
    }

    override fun setShuffle(isShuffle: Boolean) {
        player.shuffleModeEnabled = isShuffle
    }

    private fun updateMusicCount() {
        musicCountFlow.value = player.mediaItemCount
    }

    private fun getRepeatModeFromMedia(repeatModeInt: Int) = when (repeatModeInt) {
        Player.REPEAT_MODE_ALL -> RepeatMode.ALL
        Player.REPEAT_MODE_ONE -> RepeatMode.ONE
        Player.REPEAT_MODE_OFF -> RepeatMode.NONE
        else -> {
            RepeatMode.NONE
        }
    }


}
