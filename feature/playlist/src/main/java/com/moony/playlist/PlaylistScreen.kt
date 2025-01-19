package com.moony.playlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.moony.common.media.LocalMediaViewModel
import com.moony.playlist.component.MusicListItem
import com.moony.playlist.component.PlaylistHeader
import com.moony.resource.R


@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {
    val viewModel = LocalMediaViewModel.current
    val playlist = viewModel.musicPagingFlow.collectAsLazyPagingItems()

    val height = dimensionResource(R.dimen.height_size_playlist_header)
    LazyColumn(
        modifier = modifier
    ) {
        item {
            PlaylistHeader(modifier = Modifier
                .fillMaxWidth()
                .height(height))
        }
        items(playlist.itemCount) { index ->
            playlist[index]?.let { music ->
                MusicListItem(music = music)
            }
        }
    }
}
