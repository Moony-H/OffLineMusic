package com.moony.playlist

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.moony.common.composable.GradientBackground
import com.moony.common.composable.GradientBox
import com.moony.common.media.LocalMediaViewModel
import com.moony.playlist.component.MusicListItem
import com.moony.playlist.component.PlaylistHeader
import com.moony.resource.R


@Composable
fun PlaylistScreen(modifier: Modifier = Modifier, topPadding: Dp) {
    val viewModel = LocalMediaViewModel.current
    val playlist = viewModel.musicListPagingFlow.collectAsLazyPagingItems()
    val backgroundColors by viewModel.albumColors.collectAsState()

    val height = dimensionResource(R.dimen.height_size_playlist_header)
    val itemHeight = dimensionResource(R.dimen.height_music_list_item)
    //albumBackground
    val albumBackgroundMinSize = dimensionResource(R.dimen.min_size_album_background_image)
    val albumBackgroundMaxSize = dimensionResource(R.dimen.max_size_album_background_image)

    GradientBackground(
        modifier = modifier,
//        gradientBoxModifier = Modifier.sizeIn(
//            minWidth = albumBackgroundMinSize,
//            maxWidth = albumBackgroundMaxSize,
//            minHeight = albumBackgroundMinSize,
//            maxHeight = albumBackgroundMaxSize
//        ),
        colors = backgroundColors,
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding),
        ) {
            item {
                PlaylistHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height)
                )
            }
            items(playlist.itemCount) { index ->
                playlist[index]?.let { music ->
                    MusicListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(itemHeight),
                        music = music,
                        onClick = {
                            viewModel.seekMusicByIndex(index)
                            viewModel.play()
                        }
                    )
                }
            }
        }
    }
}
