package com.moony.common.composable

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


private enum class DragValue { Bottom, Top }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideUpLayout(
    modifier: Modifier = Modifier,
    bottomOffsetDp: Dp = 80.dp,
    topOffsetPx: Float = 0f,
    flingThresholdDp: Dp = 1000.dp,
    dragProcessThreshold: Float = 0.03f,
    onReachToTop: () -> Unit = {},
    onReachToBottom: () -> Unit = {},
    onDragProgressChanged: (progress: Float) -> Unit = {},
    slideContent: @Composable BoxScope.() -> Unit,
    backgroundContent: @Composable BoxScope.() -> Unit
) {
    val bottomOffsetPx = with(LocalDensity.current) { bottomOffsetDp.toPx() }
    val flingThreshold = with(LocalDensity.current) { flingThresholdDp.toPx() }


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



    val dragProgress = remember(state.offset) {
        val minAnchor = state.anchors.minAnchor()
        val maxAnchor = state.anchors.maxAnchor()
        val currentOffset = state.offset
        1f - ((currentOffset - minAnchor) / (maxAnchor - minAnchor)).coerceIn(0f, 1f)
    }


    LaunchedEffect(dragProgress) {
        onDragProgressChanged(dragProgress)
    }
    LaunchedEffect(state.currentValue) {
        if (state.currentValue == DragValue.Top) {
            onReachToTop()
        } else if (state.currentValue == DragValue.Bottom) {
            onReachToBottom()
        }
    }



    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size = it }
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
                .anchoredDraggable(state, orientation = Orientation.Vertical)
        ) {
            slideContent()
        }
    }

}

//혹시 몰라 코드 쟁여놓기

//    var beforeDragProgress by remember { mutableFloatStateOf(0f) }
//    LaunchedEffect(dragProgress) {
//        if (abs(dragProgress - beforeDragProgress) > dragProcessThreshold) {
//            beforeDragProgress = dragProgress
//            onDragProgressChanged(dragProgress)
//        }else if(dragProgress==1f ||dragProgress==0f){
//            onDragProgressChanged(dragProgress)
//        }
//    }
