package com.moony.data

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moony.data.dto.toMusic

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testMusicTiming() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val dummyGenerator = DummyGenerator(appContext)
        val a = dummyGenerator.lyricsList[2].split("\n")
        println("run!!!!!!!!!!!!")
        repeat(100) {
            val result = dummyGenerator.getRandomMusicDTOList()
            for (i in result) {
                val music=i.toMusic()
                if(music.lyrics.timingList.size!=music.lyrics.lyricsList.size){
                    println("error!!: ${music.lyrics.lyricsList[0]}")
                    println("timing: ${music.lyrics.timingList.size} ${music.lyrics.lyricsList.size}")
                }
            }
        }
    }
}
