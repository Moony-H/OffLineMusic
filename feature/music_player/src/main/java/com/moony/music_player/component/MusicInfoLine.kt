package com.moony.music_player.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.moony.resource.R

@Composable
fun MusicInfoLine(modifier: Modifier, title: String, artist: String) {

    val titleSize = dimensionResource(R.dimen.text_music_title_size)
    val artistTextSize = dimensionResource(R.dimen.text_music_artist_size)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MusicTitleAndArtist(
            modifier = Modifier.weight(1f),
            title = title,
            artist = artist,
            titleSize = titleSize,
            artistTextSize = artistTextSize
        )
        IconButton(modifier = Modifier.size(dimensionResource(R.dimen.icon_heart_size)), onClick = {

        }) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_heart),
                contentDescription = stringResource(R.string.content_heart)
            )
        }

    }
}


