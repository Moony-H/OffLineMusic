package com.moony.media_service

import android.os.Bundle
import androidx.activity.ComponentActivity

abstract class MediaServiceActivity :ComponentActivity(){
    abstract val viewModel: MediaServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.buildMediaController(this)
    }

    override fun onStop(){
        super.onStop()
        viewModel.releaseMediaController()
    }
}
