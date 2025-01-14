package com.moony.media_service.mediacontroller

import android.app.Activity
import androidx.activity.ComponentActivity
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.coroutines.CoroutineContext

class MediaControllerManagerImpl(activity: ComponentActivity) :
    MediaControllerManager by MediaControllerManagerDelegate(activity) {

}




