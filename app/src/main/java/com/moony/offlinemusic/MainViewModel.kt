package com.moony.offlinemusic

import androidx.lifecycle.ViewModel
import com.moony.common.MediaViewModel
import com.moony.domain.manager.MediaPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mediaPlayer: MediaPlayer
) : MediaViewModel(), MediaPlayer by mediaPlayer {
    override val isPlaying = mediaPlayer.isPlayingFlow
    override val currentMusic = mediaPlayer.currentMusicFlow
    override val duration = mediaPlayer.durationFlow
    override val currentPosition = mediaPlayer.currentMusicTimeLinePositionFlow
}
