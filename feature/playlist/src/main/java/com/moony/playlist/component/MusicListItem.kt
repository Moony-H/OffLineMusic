package com.moony.playlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.moony.domain.model.Music
import com.moony.resource.R
import com.moony.common.composable.ImageWithPlaceHolder
import com.moony.common.composable.MusicTitleAndArtist
import com.moony.resource.OnGreen

@Composable
fun MusicListItem(
    music: Music,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isPlaying: Boolean = false
) {


    //padding
    val paddingVertical = dimensionResource(R.dimen.padding_music_list_item_vertical)

    //textSize
    val musicTitleTextSize = dimensionResource(R.dimen.text_size_music_title_small)
    val musicArtistTextSize = dimensionResource(R.dimen.text_size_music_artist_small)

    //color
    val textColor = if (isPlaying) OnGreen else Color.Unspecified

    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(Color.Transparent)
            .padding(vertical = paddingVertical),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageWithPlaceHolder(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            url = music.imageUrl,
            contentDescription = music.title,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        MusicTitleAndArtist(
            modifier = Modifier.weight(1f),
            titleSize = musicTitleTextSize,
            artistTextSize = musicArtistTextSize,
            title = music.title,
            artist = music.artist,
            color = textColor
        )

    }
}
