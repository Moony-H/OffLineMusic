package com.moony.data

import android.content.Context

class DummyGenerator(private val context:Context){
    companion object{
        private val songNameList = listOf(
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

            )



        private const val DUMMY_SONG_URL =
            "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"

        fun <T> getRandomList(list: List<T>, length: Int = -1) =
            list.shuffled().subList(0, if (length == -1) list.size else length)
    }

}
