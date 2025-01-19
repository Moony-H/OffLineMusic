package com.moony.music_player

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moony.common.composable.ImageWithPlaceHolder
import com.moony.common.media.LocalMediaViewModel
import com.moony.domain.model.Music
import com.moony.common.composable.GradientBackground
import com.moony.domain.type.RepeatMode
import com.moony.music_player.component.LyricsText
import com.moony.music_player.component.MiniPlayer
import com.moony.music_player.component.MusicBottomIconGroup
import com.moony.music_player.component.MusicControlButtonGroup
import com.moony.music_player.component.MusicInfoLine
import com.moony.music_player.component.MusicSlider
import com.moony.music_player.component.TopBar
import com.moony.resource.R

@Composable
fun MusicScreen(
    scaffoldTopPadding: Dp,
    modifier: Modifier = Modifier,
    alpha: Float = 1f
) {
    val mediaViewModel = LocalMediaViewModel.current


    //****state****

    //music
    val musicState = mediaViewModel.currentMusicFlow.collectAsState()

    //Lyrics
    val currentLyrics by mediaViewModel.currentLyricsPartFlow.collectAsState()
    val nextLyrics by mediaViewModel.nextLyricsPartFlow.collectAsState()

    //position
    val currentPosition by mediaViewModel.currentPositionFlow.collectAsState(0L)
    val totalPosition by mediaViewModel.durationFlow.collectAsState()

    //playing
    val isPlaying by mediaViewModel.isPlayingFlow.collectAsState()

    //repeatMode
    val repeatMode by mediaViewModel.repeatModeFlow.collectAsState()

    //shuffleMode
    val isShuffle by mediaViewModel.isShuffleFlow.collectAsState()

    //album
    val albumImage by mediaViewModel.albumImage.collectAsState()
    val backgroundColors by mediaViewModel.albumColors.collectAsState()


    //****Resource****

    //albumImage
    val albumImageMinSize = dimensionResource(R.dimen.min_size_album_image)
    val albumImageMaxSize = dimensionResource(R.dimen.max_size_album_background_image)


    //albumBackground
    val albumBackgroundMinSize = dimensionResource(R.dimen.min_size_album_background_image)
    val albumBackgroundMaxSize = dimensionResource(R.dimen.max_size_album_background_image)


    //roundedCornerRadius
    val albumCornerRadius = dimensionResource(R.dimen.album_image_rounded_radius)

    //miniPlayer
    val bottomMiniPlayerHeight = dimensionResource(R.dimen.height_bottom_mini_player)

    //
    val screenHorizontalPadding = dimensionResource(R.dimen.screen_padding_horizontal)

    //music
    val music = musicState.value ?: Music.getEmpty()


    Box(
        modifier = modifier
    ) {
        GradientBackground(
            modifier = modifier.fillMaxSize(),
            gradientBoxModifier = Modifier
                .sizeIn(
                    minWidth = albumBackgroundMinSize,
                    maxWidth = albumBackgroundMaxSize,
                    minHeight = albumBackgroundMinSize,
                    maxHeight = albumBackgroundMaxSize
                )
                .fillMaxWidth()
                .aspectRatio(1f),
            colors = backgroundColors,
        ) {
            Column(
                modifier = Modifier.alpha(alpha)
                    .fillMaxSize()
                    .padding(
                        top = scaffoldTopPadding,
                        start = screenHorizontalPadding,
                        end = screenHorizontalPadding
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                TopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.screen_padding_vertical))
                )
                Spacer(modifier = Modifier.weight(2f))

                ImageWithPlaceHolder(
                    bitmap = albumImage,
                    modifier = Modifier
                        .sizeIn(
                            minWidth = albumImageMinSize,
                            maxWidth = albumImageMaxSize,
                            minHeight = albumImageMinSize,
                            maxHeight = albumImageMaxSize
                        )
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.screen_padding_horizontal))
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(albumCornerRadius)),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.content_alum_image),
                )
                Spacer(modifier = Modifier.height(12.dp))
                LyricsText(currentLyrics, nextLyrics)

                Spacer(modifier = Modifier.weight(2f))

                MusicInfoLine(modifier = Modifier.fillMaxWidth(), music.title, music.artist)
                Spacer(modifier = Modifier.height(20.dp))
                MusicSlider(
                    currentPosition,
                    totalPosition,
                    modifier = Modifier.fillMaxWidth(),
                    onRelease = {
                        mediaViewModel.seekTo(it)

                    }
                )
                Spacer(modifier = Modifier.height(20.dp))


                MusicControlButtonGroup(
                    modifier = Modifier.fillMaxWidth(),
                    isPlaying = isPlaying,
                    repeatMode = repeatMode,
                    isShuffle = isShuffle,
                    onPlayButtonClicked = mediaViewModel::play,
                    onPauseButtonClicked = mediaViewModel::pause,
                    onPrevButtonClicked = mediaViewModel::previous,
                    onNextButtonClicked = mediaViewModel::next,
                    onRepeatButtonClicked = {
                        mediaViewModel.setRepeatMode(repeatMode.next())
                    },
                    onShuffleButtonClicked = {
                        mediaViewModel.setShuffle(!isShuffle)
                    }
                )
                Spacer(modifier = Modifier.weight(3f))

                MusicBottomIconGroup(
                    modifier = Modifier.fillMaxWidth(),
                    onLyricsClick = {},
                    onPlaylistClick = {}
                )
                Spacer(modifier = Modifier.height(12.dp))

            }
        }
        MiniPlayer(
            modifier = Modifier
                .alpha(1f - alpha)
                .fillMaxWidth()
                .height(bottomMiniPlayerHeight)
        )
    }
}


