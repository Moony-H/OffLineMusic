package com.moony.domain.model


data class Music(
    val id: Long,
    val title: String,
    val musicUrl: String,
    val artist: String,
    val imageUrl: String,
    val lyrics: Lyrics,
) {
    companion object {
        const val LYRICS_LIST_KEY = "LYRICS_LIST"
        const val LYRICS_TIMING_LIST_KEY = "LYRICS_TIMING_LIST"

        fun getEmpty() = Music(
            id = 0L,
            title = "노래를 선택해주세요",
            musicUrl = "",
            artist = "",
            imageUrl ="",// "https://github.com/user-attachments/assets/7eb6c771-1246-47a7-a3df-5ce7d3b3bd31",
            lyrics = Lyrics(listOf(), listOf())
        )
    }
}

