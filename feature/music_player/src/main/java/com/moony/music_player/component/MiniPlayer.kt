package com.moony.music_player.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.moony.common.composable.ImageWithPlaceHolder
import com.moony.common.composable.MusicTitleAndArtist
import com.moony.common.media.LocalMediaViewModel
import com.moony.domain.model.Music
import com.moony.resource.MiniPlayerBackground
import com.moony.resource.MiniPlayerProgressBackground
import com.moony.resource.OnGreen
import com.moony.resource.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniPlayer(modifier: Modifier = Modifier) {

    val viewModel = LocalMediaViewModel.current

    val currentPosition by viewModel.currentPositionFlow.collectAsState(0L)
    val totalDuration by viewModel.durationFlow.collectAsState(0L)
    val albumImage by viewModel.albumImage.collectAsState()
    val isPlaying by viewModel.isPlayingFlow.collectAsState()


    val albumDescription = stringResource(R.string.content_alum_image)

    val musicState by viewModel.currentMusicFlow.collectAsState()
    val music = musicState ?: Music.getEmpty()

    //icon
    val playIconId = R.drawable.icon_play
    val pauseIconId = R.drawable.icon_pause
    val mediaIcon = ImageVector.vectorResource(if (isPlaying) pauseIconId else playIconId)
    val nextIcon = ImageVector.vectorResource(R.drawable.icon_skip_next)
    val playlistIcon = ImageVector.vectorResource(R.drawable.icon_playlist)

    //description
    val playDescriptionId = R.string.content_play
    val pauseDescriptionId = R.string.content_pause
    val mediaDescription = stringResource(if (isPlaying) pauseDescriptionId else playDescriptionId)
    val nextDescription = stringResource(R.string.content_next)
    val playlistDescription = stringResource(R.string.content_playlist)

    //size
    val musicTitleTextSize = dimensionResource(R.dimen.text_size_music_title_small)
    val musicArtistTextSize = dimensionResource(R.dimen.text_size_music_artist_small)

    val duration = if (totalDuration == 0L) 1 else totalDuration
    val currentPositionFloat = (currentPosition.toFloat() / duration)


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(0.dp),
            progress = { currentPositionFloat },
            color = OnGreen,
            trackColor = MiniPlayerProgressBackground,
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MiniPlayerBackground)
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageWithPlaceHolder(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                bitmap = albumImage,
                contentDescription = albumDescription,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            MusicTitleAndArtist(
                title = music.title,
                artist = music.artist,
                modifier = Modifier.weight(1f),
                titleSize = musicTitleTextSize,
                artistTextSize = musicArtistTextSize
            )
            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .aspectRatio(1f),
                onClick = if (isPlaying) viewModel::pause else viewModel::play
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = mediaIcon,
                    contentDescription = mediaDescription
                )

            }

            IconButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                onClick = viewModel::next
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = nextIcon,
                    contentDescription = nextDescription
                )

            }
            IconButton(modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = playlistIcon,
                    contentDescription = playlistDescription
                )

            }

        }
    }
}
