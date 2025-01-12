package com.moony.data.dto

import com.moony.domain.model.Music

internal data class MusicDTO(
    val title: String,
    val musicUrl: String,
    val artist: String,
    val imageUrl: String,
    val lyrics: String,
)

internal fun MusicDTO.toMusic() = Music(
    title = title,
    musicUrl = musicUrl,
    artist = artist,
    imageUrl = imageUrl,
    lyrics = lyrics,
)
