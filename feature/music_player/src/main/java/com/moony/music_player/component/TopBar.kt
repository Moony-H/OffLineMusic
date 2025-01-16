package com.moony.music_player.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.moony.resource.R

@Composable
fun TopBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal),
                vertical = dimensionResource(R.dimen.screen_padding_vertical)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(dimensionResource(R.dimen.icon_eq_size)),
            onClick = {

            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_tune),
                contentDescription = stringResource(R.string.content_equalizer)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.size(dimensionResource(R.dimen.icon_download_size)),
            onClick = {

            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_download),
                contentDescription = stringResource(R.string.content_download)
            )

        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacer_icon_horizontal)))
        IconButton(
            modifier = Modifier.size(dimensionResource(R.dimen.icon_menu_size)),
            onClick = {

            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_more_horiz),
                contentDescription = stringResource(R.string.content_menu)
            )

        }
    }
}

@Composable
@Preview
fun TopBarPreview() {
    TopBar(modifier = Modifier.fillMaxSize())
}
