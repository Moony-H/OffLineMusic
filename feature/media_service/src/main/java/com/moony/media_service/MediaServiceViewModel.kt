package com.moony.media_service

import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors


//메모리 누수 관리 필요(외부 context 사용함.)
abstract class MediaServiceViewModel : ViewModel() {
    private var _mediaController: MediaController? = null
    protected val mediaController: MediaController?
        get() = _mediaController

    private var mediaControllerFuture: ListenableFuture<MediaController>? = null


    private var _mediaBrowser: MediaBrowser? = null
    protected val mediaBrowser: MediaBrowser?
        get() = _mediaBrowser
    private var mediaBrowserFuture: ListenableFuture<MediaBrowser>? = null


    fun buildMediaController(context: Context) {
        val sessionToken = SessionToken(context, ComponentName(context, MediaService::class.java))
        //토큰으로 MediaController의 future 생성
        mediaControllerFuture = MediaController.Builder(context, sessionToken)
            .buildAsync()
        mediaControllerFuture?.let {
            it.addListener({
                _mediaController = it.get()
            }, MoreExecutors.directExecutor())
        }

        mediaBrowserFuture = MediaBrowser.Builder(context, sessionToken)
            .buildAsync()

        mediaBrowserFuture?.let {
            it.addListener({
                _mediaBrowser = it.get()

            }, MoreExecutors.directExecutor())
        }
    }

    fun releaseMediaController() {
        _mediaController?.release()
        mediaControllerFuture?.let { MediaController.releaseFuture(it) }
        mediaControllerFuture = null
        _mediaController = null

        _mediaBrowser?.release()
        mediaBrowserFuture?.let { MediaBrowser.releaseFuture(it) }
        mediaBrowserFuture = null
        _mediaBrowser = null
    }
}
