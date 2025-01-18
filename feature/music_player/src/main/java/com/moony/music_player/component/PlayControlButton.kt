package com.moony.music_player.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.moony.resource.R

@Composable
fun PlayControlButton(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onPrevButtonClicked: () -> Unit,
    onPauseButtonClicked: () -> Unit,
    onPlayButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    //icon


    val middleButtonIconId = if (isPlaying) R.drawable.icon_pause else R.drawable.icon_play
    val middleButtonIcon = ImageVector.vectorResource(middleButtonIconId)
    val prevIcon = ImageVector.vectorResource(R.drawable.icon_skip_previous)
    val nextIcon = ImageVector.vectorResource(R.drawable.icon_skip_next)

    //description
    val middleButtonDescriptionId = if (isPlaying) R.string.content_pause else R.string.content_play
    val middleButtonDescription = stringResource(middleButtonDescriptionId)
    val prevDescription = stringResource(R.string.content_previous)
    val nextDescription = stringResource(R.string.content_next)


    val middleButtonCallback = if (isPlaying) onPauseButtonClicked else onPlayButtonClicked

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            onClick = onPrevButtonClicked
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = prevIcon,
                contentDescription = prevDescription
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            onClick = middleButtonCallback
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = middleButtonIcon,
                contentDescription = middleButtonDescription
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            onClick = onNextButtonClicked
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = nextIcon,
                contentDescription = nextDescription
            )
        }
    }
}
