package com.moony.music_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moony.common.media.LocalMediaViewModel
import com.moony.domain.model.Music
import com.moony.music_player.component.BlurredColumn
import com.moony.music_player.component.LyricsText
import com.moony.music_player.component.MusicControlButtonGroup
import com.moony.music_player.component.MusicInfoLine
import com.moony.music_player.component.MusicSlider
import com.moony.music_player.component.TopBar
import com.moony.resource.BackgroundBlack
import com.moony.resource.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
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

    //blurredBackgroundImage
    val blurredBackgroundImage by mediaViewModel.backgroundBlurImage.collectAsState()
    val albumImage by mediaViewModel.albumImage.collectAsState()


    //****Resource****

    //albumImage
    val albumImageMinSize = dimensionResource(R.dimen.min_size_album_image)
    val albumImageMaxSize = dimensionResource(R.dimen.max_size_album_background_image)

    //roundedCornerRadius
    val albumCornerRadius = dimensionResource(R.dimen.albumImageRoundedRadius)


    //music
    val music = musicState.value ?: Music.getEmpty()


    BlurredColumn(
        bitmap = blurredBackgroundImage,
        modifier = modifier
            .background(color = BackgroundBlack)
            .padding(horizontal = dimensionResource(R.dimen.screen_padding_horizontal)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.screen_padding_vertical))
        )
        GlideImage(
            model = albumImage,
            modifier = Modifier
                .sizeIn(
                    minWidth = albumImageMinSize,
                    maxWidth = albumImageMaxSize,
                    minHeight = albumImageMinSize,
                    maxHeight = albumImageMaxSize
                ).fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.screen_padding_horizontal))
                .aspectRatio(1f)
                .clip(RoundedCornerShape(albumCornerRadius)),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.content_alum_image),
        )
        Spacer(modifier = Modifier.height(12.dp))
        LyricsText(currentLyrics, nextLyrics)
        MusicInfoLine(modifier = Modifier.fillMaxWidth(), music.title, music.artist)
        MusicSlider(
            currentPosition,
            totalPosition,
            modifier = Modifier.fillMaxWidth(),
            onRelease = {
                mediaViewModel.seekTo(it)

            }
        )

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

    }
}
