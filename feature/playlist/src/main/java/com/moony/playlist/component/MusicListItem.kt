package com.moony.playlist.component

import androidx.compose.foundation.background
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

@Composable
fun MusicListItem(modifier: Modifier = Modifier, music: Music) {

    val height = dimensionResource(R.dimen.height_music_list_item)

    //padding
    val paddingHorizontal = dimensionResource(R.dimen.padding_music_list_item_horizontal)
    val paddingVertical = dimensionResource(R.dimen.padding_music_list_item_vertical)

    //textSize
    val musicTitleTextSize = dimensionResource(R.dimen.text_size_music_title)
    val musicArtistTextSize = dimensionResource(R.dimen.text_size_music_artist)

    Row(
        modifier = modifier
            .height(height)
            .background(Color.Transparent)
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
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
            titleSize = musicTitleTextSize,
            artistTextSize = musicArtistTextSize,
            title = music.title,
            artist = music.artist,
            modifier = Modifier.weight(1f)
        )

    }
}
