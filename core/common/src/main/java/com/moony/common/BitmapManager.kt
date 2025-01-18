package com.moony.common

import android.graphics.Bitmap
import android.graphics.HardwareRenderer
import android.graphics.PixelFormat
import android.graphics.RenderEffect
import android.graphics.RenderNode
import android.graphics.Shader
import android.hardware.HardwareBuffer
import android.media.ImageReader
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object BitmapManager {

    suspend fun extractColorFromBitmap(bitmap: Bitmap): List<Color> =
        suspendCoroutine { continuation ->
            Palette.from(bitmap).addFilter({ _, hsl ->
                hsl[2]<=0.7f
            }).maximumColorCount(4).generate { palette ->
                continuation.resume(palette?.swatches?.map { Color(it.rgb) } ?: emptyList())
            }
        }

    fun getBlurredBitmap(bitmap: Bitmap, radius: Float): Bitmap {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return bitmap
        val width = bitmap.width
        val height = bitmap.height
        val imageReader = ImageReader.newInstance(
            width, height,
            PixelFormat.RGBA_8888, 1,
            HardwareBuffer.USAGE_GPU_SAMPLED_IMAGE or HardwareBuffer.USAGE_GPU_COLOR_OUTPUT
        )
        val renderNode = RenderNode("BlurEffect")
        val hardwareRenderer = HardwareRenderer()

        hardwareRenderer.setSurface(imageReader.surface)
        hardwareRenderer.setContentRoot(renderNode)
        renderNode.setPosition(0, 0, imageReader.width, imageReader.height)
        val blurRenderEffect = RenderEffect.createBlurEffect(
            radius, radius,
            Shader.TileMode.MIRROR
        )
        renderNode.setRenderEffect(blurRenderEffect)
        val renderCanvas = renderNode.beginRecording()
        renderCanvas.drawBitmap(bitmap, 0f, 0f, null)
        renderNode.endRecording()
        hardwareRenderer.createRenderRequest()
            .setWaitForPresent(true)
            .syncAndDraw()
        val image = imageReader.acquireNextImage() ?: throw RuntimeException("No Image")
        val hardwareBuffer = image.hardwareBuffer ?: throw RuntimeException("No HardwareBuffer")
        val result = Bitmap.wrapHardwareBuffer(hardwareBuffer, null)
            ?: throw RuntimeException("Create Bitmap Failed")
        return result
    }
}
