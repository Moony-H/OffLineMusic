package com.moony.media_service

import android.os.Bundle
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import com.moony.domain.model.Music
import com.moony.common.toStringOrEmpty
import com.moony.domain.type.PlayerError

fun Music.toMediaItem() =
    MediaItem.Builder().setMediaId(id.toString()).setUri(musicUrl).setMediaMetadata(
        MediaMetadata.Builder().setTitle(title).setArtworkUri(imageUrl.toUri()).setArtist(artist)
            .setExtras(
                Bundle().apply { putString(Music.LYRICS_KEY, lyrics) }
            )
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

fun PlaybackException.toPlayerError() = when (this.errorCode % 1000) {
    2 -> PlayerError.Network(this.errorCodeName, this.message)
    4 -> PlayerError.Decoding(this.errorCodeName, this.message)
    else -> PlayerError.Unexpected(this.errorCodeName, this.message)
}
