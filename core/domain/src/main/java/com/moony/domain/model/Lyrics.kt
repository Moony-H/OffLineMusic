package com.moony.domain.model

data class Lyrics(
    val lyricsList: List<String>,
    val timingList: List<Long>
) {
    fun getLyricsPartByIndex(index: Int) = lyricsList.getOrNull(index)
    fun getPrevLyricsPartByIndex(index: Int) = lyricsList.getOrNull(index - 1)
    fun getNextLyricsPartByIndex(index: Int) = lyricsList.getOrNull(index + 1)

    fun getLyricsPartByCurrentMillis(currentMillis: Long): String {
        return lyricsList[getIndexFromCurrentMillis(currentMillis)]
    }

    fun getPrevLyricsPartByCurrentMillis(currentMillis: Long): String? {
        return lyricsList.getOrNull(getIndexFromCurrentMillis(currentMillis))
    }

    //이분탐색
    fun getIndexFromCurrentMillis(currentMillis: Long): Int {
        if (currentMillis < 0) return -1
        var l = 0
        var r = timingList.size - 1
        var index: Int
        while (l <= r) {
            index = l + r / 2
            if (timingList[index] < currentMillis)
                l = index + 1
            else if (timingList[index] > currentMillis)
                r = index - 1
            else
                return index
        }
        return r
    }
}
