package com.moony.playlist.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.moony.resource.R

@Composable
fun PlaylistHeader(modifier: Modifier = Modifier) {

    val title = stringResource(R.string.text_playlist)
    val textSize = dimensionResource(R.dimen.text_size_playlist_header).value.sp
    Row(modifier = Modifier) {
        Text(
            text = title,
            modifier = Modifier.fillMaxSize(),
            fontSize = textSize
        )
    }

}
