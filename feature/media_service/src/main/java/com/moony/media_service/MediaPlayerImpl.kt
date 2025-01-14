package com.moony.media_service

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import com.moony.domain.manager.MediaPlayer
import com.moony.domain.model.Music
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MediaPlayerImpl @Inject constructor(
    private val player: Player
) : MediaPlayer {
    override val isPlayingFlow = MutableStateFlow(false)
    override val currentMusicTimeLinePositionFlow = MutableStateFlow(0L)
    override val durationFlow = MutableStateFlow(0L)
    override val currentMusicFlow = MutableStateFlow<Music?>(null)

    //toMusic 파일 옮기기(아마 여기 모듈로?)
    init {
        player.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                currentMusicFlow.value = mediaItem?.toMusic()
                durationFlow.value = player.duration
            }

            override fun onTimelineChanged(timeline: Timeline, reason: Int) {
                super.onTimelineChanged(timeline, reason)
                currentMusicTimeLinePositionFlow.value = player.currentPosition
            }


            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                isPlayingFlow.value = isPlaying
            }

        })
    }

    override fun play() {
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

    override fun addMusic(music: Music) {
        player.addMediaItem(music.toMediaItem())
    }

    override fun addMusic(index: Int, music: Music) {
        player.addMediaItem(index, music.toMediaItem())
    }

    override fun addMusics(musics: List<Music>) {
        player.addMediaItems(musics.map { it.toMediaItem() })
    }

    override fun addMusics(index: Int, musics: List<Music>) {
        player.addMediaItems(index, musics.map { it.toMediaItem() })
    }


}
