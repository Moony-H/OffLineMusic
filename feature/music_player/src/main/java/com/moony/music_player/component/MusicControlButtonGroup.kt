package com.moony.music_player.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.moony.domain.type.RepeatMode
import com.moony.music_player.extension.getDescriptionId
import com.moony.music_player.extension.getIconId
import com.moony.resource.DisableMusicIconGray
import com.moony.resource.OnGreen
import com.moony.resource.R

@Composable
fun MusicControlButtonGroup(
    isPlaying: Boolean,
    repeatMode: RepeatMode,
    isShuffle: Boolean,
    modifier: Modifier = Modifier,
    onRepeatButtonClicked: () -> Unit,
    onShuffleButtonClicked: () -> Unit,
    onPrevButtonClicked: () -> Unit,
    onPauseButtonClicked: () -> Unit,
    onPlayButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {

    //icon
    val shuffleIcon = ImageVector.vectorResource(R.drawable.icon_shuffle)
    val repeatModeIcon = ImageVector.vectorResource(repeatMode.getIconId())

    //description
    val shuffleDescriptionId =
        if (isShuffle) R.string.content_shuffle_on else R.string.content_shuffle_off
    val shuffleDescription = stringResource(shuffleDescriptionId)
    val repeatModeDescription = stringResource(repeatMode.getDescriptionId())


    //iconSize
    val shuffleIconSize = dimensionResource(R.dimen.icon_shuffle_size)
    val repeatIconSize = dimensionResource(R.dimen.icon_repeat_size)


    //playControlButton
    val playControlMinWidth = dimensionResource(R.dimen.play_control_button_group_min_width)
    val playControlMinHeight = dimensionResource(R.dimen.play_control_button_group_min_height)
    val playControlMaxWidth = dimensionResource(R.dimen.play_control_button_group_max_width)
    val playControlMaxHeight = dimensionResource(R.dimen.play_control_button_group_max_height)

    //tint
    val shuffleTint = if (isShuffle) OnGreen else DisableMusicIconGray
    val repeatModeTint = if (repeatMode == RepeatMode.NONE) DisableMusicIconGray else OnGreen



    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(modifier = Modifier.size(shuffleIconSize), onClick = onShuffleButtonClicked) {
            Icon(
                imageVector = shuffleIcon,
                contentDescription = shuffleDescription,
                tint = shuffleTint
            )
        }
        Spacer(modifier = Modifier.widthIn(min = 48.dp))
        PlayControlButton(
            modifier = Modifier
                .weight(1f, false)
                .sizeIn(
                    minWidth = playControlMinWidth,
                    minHeight = playControlMinHeight,
                    maxWidth = playControlMaxWidth,
                    maxHeight = playControlMaxHeight
                ),
            onPrevButtonClicked = onPrevButtonClicked,
            onPauseButtonClicked = onPauseButtonClicked,
            onPlayButtonClicked = onPlayButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
            isPlaying = isPlaying
        )
        Spacer(modifier = Modifier.widthIn(min = 48.dp))
        IconButton(
            modifier = Modifier.size(repeatIconSize), onClick = onRepeatButtonClicked
        ) {
            Icon(
                imageVector = repeatModeIcon,
                contentDescription = repeatModeDescription,
                tint = repeatModeTint
            )
        }
    }


}
