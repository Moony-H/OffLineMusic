package com.moony.domain.model

data class Lyrics(
    val lyricsList: List<String>,
    val timingList: List<Long>
) {
    fun getLyricsPartByIndex(index: Int) = lyricsList.getOrNull(index)
    fun getPrevLyricsPartByIndex(index: Int) = lyricsList.getOrNull(index - 1)
    fun getNextLyricsPartByIndex(index: Int) = lyricsList.getOrNull(index + 1)

    fun getLyricsPartByCurrentMillis(currentMillis: Long): String {
        return lyricsList.getOrNull(getIndexFromCurrentMillis(currentMillis)) ?: ""
    }

    fun getNextLyricsPartByCurrentMillis(currentMillis: Long): String? {
        return lyricsList.getOrNull(getIndexFromCurrentMillis(currentMillis) + 1)
    }

    //이분탐색
    fun getIndexFromCurrentMillis(currentMillis: Long): Int {
        if (currentMillis < 0) return -1
        var l = 0
        var r = timingList.size - 1
        var result = -1
        while (l <= r) {
            val index = (r + l) / 2
            if (timingList[index] <= currentMillis) {
                result = index
                l = index + 1
            } else
                r = index - 1
        }
        return result
    }
}
