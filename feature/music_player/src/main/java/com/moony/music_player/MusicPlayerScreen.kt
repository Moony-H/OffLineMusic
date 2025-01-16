package com.moony.music_player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.moony.common.media.LocalMediaViewModel
import com.moony.domain.model.Music
import com.moony.music_player.component.LyricsText
import com.moony.music_player.component.TopBar
import com.moony.resource.BackgroundBlack
import com.moony.resource.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
) {
    val mediaViewModel = LocalMediaViewModel.current
    val musicState = mediaViewModel.currentMusicFlow.collectAsState()
    val music = musicState.value ?: Music.getEmpty()

    val imageMinSize = dimensionResource(R.dimen.min_size_music_image)
    val imageMaxSize = dimensionResource(R.dimen.max_size_music_image)
    Column(
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
            modifier = Modifier
                .sizeIn(
                    minWidth = imageMinSize,
                    maxWidth = imageMaxSize,
                    minHeight = imageMinSize,
                    maxHeight = imageMaxSize
                )
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.screen_padding_horizontal))

                .aspectRatio(1f),
            model = music.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.content_alum_image)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LyricsText("이건 현재 텍스트","이건 나중 텍스트")


    }
}
