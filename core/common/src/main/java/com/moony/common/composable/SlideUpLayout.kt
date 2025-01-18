package com.moony.common.composable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


private enum class DragValue { Bottom, Top }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideUpLayout(
    modifier: Modifier = Modifier,
    bottomOffsetPx: Float = with(LocalDensity.current) { 80.dp.toPx() },
    topOffsetPx: Float = 0f,
    flingThreshold: Float = with(LocalDensity.current) { 2000.dp.toPx() },
    onReachToTop: () -> Unit = {},
    onReachToBottom: () -> Unit = {},
    onDragProgressChanged: (progress: Float) -> Unit = {},
    slideContent: @Composable BoxScope.() -> Unit,
    backgroundContent: @Composable BoxScope.() -> Unit
) {

    var size by remember { mutableStateOf(IntSize.Zero) }
    val state by remember {
        derivedStateOf {
            AnchoredDraggableState(
                initialValue = DragValue.Bottom,
                anchors = DraggableAnchors {
                    DragValue.Bottom at size.height.toFloat() - bottomOffsetPx
                    DragValue.Top at 0f + topOffsetPx
                },
                positionalThreshold = { it * 0.5f },
                velocityThreshold = { flingThreshold },
                animationSpec = tween()
            )
        }

    }
    LaunchedEffect(state.currentValue) {
        if (state.currentValue == DragValue.Top) {
            onReachToTop()
        } else if (state.currentValue == DragValue.Bottom) {
            onReachToBottom()
        }
    }

    LaunchedEffect(state.progress) {
        val minAnchor = state.anchors.minAnchor()
        val maxAnchor = state.anchors.maxAnchor()
        val currentOffset = state.offset
        val dragProgress = ((currentOffset - minAnchor) / (maxAnchor - minAnchor))
            .coerceIn(0f, 1f)
        onDragProgressChanged(dragProgress)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size = it }
            .anchoredDraggable(state, orientation = Orientation.Vertical)
    ) {
        backgroundContent()
        Box(
            Modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = state
                            .requireOffset()
                            .roundToInt()
                    )
                }
                .fillMaxSize()

        ) {
            slideContent()
        }
    }

}

