package com.moony.common.media

import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.moony.domain.model.Music
import com.moony.common.toStringOrEmpty

fun Music.toMediaItem() =
    MediaItem.Builder().setMediaId(id.toString()).setUri(musicUrl).setMediaMetadata(
        MediaMetadata.Builder().setTitle(title).setArtworkUri(imageUrl.toUri()).setArtist(artist)
            .build()
    ).build()

fun MediaItem.toMusic(): Music {
    val id = mediaId.toLongOrNull() ?: -1L
    val title = mediaMetadata.title.toStringOrEmpty()
    val musicUrl = requestMetadata.mediaUri.toStringOrEmpty()
    val artist = mediaMetadata.artist.toStringOrEmpty()
    val imageUrl = mediaMetadata.artworkUri.toStringOrEmpty()
    val lyrics = mediaMetadata.extras?.getString(Music.LYRICS_KEY) ?: ""

    return Music(id = id, title = title, musicUrl = musicUrl, artist, imageUrl, lyrics)
}
