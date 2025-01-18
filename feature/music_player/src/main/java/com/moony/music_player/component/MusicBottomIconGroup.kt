package com.moony.music_player.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.moony.resource.DisableMusicIconGray
import com.moony.resource.R

@Composable
fun MusicBottomIconGroup(
    modifier: Modifier,
    onLyricsClick: () -> Unit,
    onPlaylistClick: () -> Unit
) {

    val lyricsIcon = ImageVector.vectorResource(R.drawable.icon_lyrics)
    val playlistIcon = ImageVector.vectorResource(R.drawable.icon_playlist)


    val lyricsIconSize = dimensionResource(R.dimen.icon_lyrics_size)
    val playlistIconSize = dimensionResource(R.dimen.icon_playlist_size)

    val lyricsContentDescription = stringResource(R.string.content_lyrics)
    val playlistContentDescription = stringResource(R.string.content_playlist)

    val iconTintColor = DisableMusicIconGray
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier.size(lyricsIconSize),
            onClick = onLyricsClick,
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = lyricsIcon,
                contentDescription = lyricsContentDescription,
                tint = iconTintColor
            )
        }
        IconButton(
            modifier = Modifier.size(playlistIconSize),
            onClick = onPlaylistClick
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = playlistIcon,
                contentDescription = playlistContentDescription,
                tint = iconTintColor
            )
        }
    }
}
