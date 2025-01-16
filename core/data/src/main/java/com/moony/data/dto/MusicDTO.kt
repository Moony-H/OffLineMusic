package com.moony.data.dto

import com.moony.domain.model.Lyrics
import com.moony.domain.model.Music

internal data class MusicDTO(
    val id: Long,
    val title: String,
    val musicUrl: String,
    val artist: String,
    val imageUrl: String,
    val lyrics: String,
    val lyricsTimingList: List<Long>
)

internal fun MusicDTO.toMusic() = Music(
    id = id,
    title = title,
    musicUrl = musicUrl,
    artist = artist,
    imageUrl = imageUrl,
    lyrics = Lyrics(lyrics.split("\n"), lyricsTimingList),
)
