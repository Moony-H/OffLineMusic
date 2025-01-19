package com.moony.data

import android.content.Context
import com.moony.data.dto.MusicDTO
import com.moony.domain.model.Lyrics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.random.Random

internal class DummyGenerator @Inject constructor(@ApplicationContext private val context: Context) {

    private var id = 0L
        get() = ++field

    private val songTitleList = listOf(
        "한 페이지가 될 수 있게",
        "うたたね",
        "work, shit, sleep",
        "Lemon",
        "어떻게 이별까지 사랑하겠어 널 사랑하는 거지",
        "なんでもないや",
        "Back In Black",
        "スパークル",
        "Bubble Gum",
        "シルエット",
        "KICK BACK",
    )

    private val dummyImageUrlList = listOf(
        "https://fastly.picsum.photos/id/11/2500/1667.jpg?hmac=xxjFJtAPgshYkysU_aqx2sZir-kIOjNR9vx0te7GycQ",
        "https://fastly.picsum.photos/id/14/2500/1667.jpg?hmac=ssQyTcZRRumHXVbQAVlXTx-MGBxm6NHWD3SryQ48G-o",
        "https://fastly.picsum.photos/id/20/3670/2462.jpg?hmac=CmQ0ln-k5ZqkdtLvVO23LjVAEabZQx2wOaT4pyeG10I",
        "https://github.com/user-attachments/assets/ebff661b-d837-4f40-9b17-84c449045f02",
        "https://github.com/user-attachments/assets/7eb6c771-1246-47a7-a3df-5ce7d3b3bd31"
    )

    private val artistNameList = listOf(
        "한문휘",
        "ハンムンヒ",
        "데이식스",
        "Post Malone",
        "米津玄師",
        "RADWIMPS",
        "Leina",
        "jisokury clup",
        "AKMU",
        "AC/DC"
    )

    val lyricsList = context.resources.getStringArray(R.array.song_lyrics).toList()


    private val dummySongUrl = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"


    fun searchMusicByTitle(title: String): MusicDTO? {
        val resultTitle = songTitleList.find { it in title }
        val lyricsString = getRandom(lyricsList)
        val lyricsStringList = lyricsString.split("\n")
        val timingList = List(lyricsStringList.size) { index ->
            index * (60000L / lyricsStringList.size)
        }
        return resultTitle?.let {
            MusicDTO(
                id = id,
                title = it,
                musicUrl = dummySongUrl,
                getRandom(artistNameList),
                getRandom(dummyImageUrlList),
                getRandom(lyricsList),
                lyricsTimingList = timingList
            )
        }
    }

    fun getRandomMusicDTOList(size: Int = 50) = Array(size) {
        getRandomMusicDTO()
    }.toList()


    private fun getRandomMusicDTO(): MusicDTO {
        val lyricsString = getRandom(lyricsList)
        val lyricsStringList = lyricsString.split("\n")
        val timingList = List(lyricsStringList.size) { index ->
            index * (60000L / lyricsStringList.size)
        }
        return MusicDTO(
            id = id,
            title = getRandom(songTitleList),
            musicUrl = dummySongUrl,
            artist = getRandom(artistNameList),
            imageUrl = getRandom(dummyImageUrlList),
            lyrics = lyricsString,
            lyricsTimingList = timingList
        )
    }

    private fun <T> getRandomList(list: List<T>, length: Int = -1) =
        list.shuffled().subList(0, if (length == -1) list.size else length)

    private fun <T> getRandom(list: List<T>) = list[Random.nextInt(list.size)]

}
