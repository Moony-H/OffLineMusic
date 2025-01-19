package com.moony.common.composable

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.moony.resource.R

@Composable
fun ImageWithPlaceHolder(
    modifier: Modifier = Modifier,
    bitmap: Bitmap?,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val noImage = painterResource(R.drawable.no_image_placeholder)
    bitmap?.let {
        Image(
            modifier = modifier,
            bitmap = it.asImageBitmap(),
            contentDescription = contentDescription,
            contentScale = contentScale
        )
    } ?: run {
        Image(
            modifier = modifier,
            painter = noImage,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun ImageWithPlaceHolder(
    imageBitmap: ImageBitmap?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,

    ) {
    val noImage = painterResource(R.drawable.no_image_placeholder)
    imageBitmap?.let {
        Image(
            modifier = modifier,
            bitmap = it,
            contentDescription = contentDescription
        )
    } ?: run {
        Image(
            modifier = modifier,
            painter = noImage,
            contentDescription = contentDescription
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageWithPlaceHolder(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,

    ) {
    GlideImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,

        )

}
