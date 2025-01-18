package com.moony.music_player.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.moony.resource.R
import com.moony.resource.DisableMusicIconGray
import com.moony.resource.OnGreen

@SuppressLint("ReturnFromAwaitPointerEventScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicSlider(
    currentPosition: Long,
    totalDuration: Long,
    modifier: Modifier = Modifier,
    onRelease: (Long) -> Unit
) {
    val duration = if (totalDuration == 0L) 1 else totalDuration
    val currentPositionFloat = (currentPosition.toFloat() / duration)
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val currentPositionTextSize =
        dimensionResource(R.dimen.text_music_current_position_size).value.sp
    val totalDurationTextSize = dimensionResource(R.dimen.text_music_total_duration_size).value.sp
    Column(modifier = modifier) {
        Slider(
            value = if (!isDragging) currentPositionFloat else sliderPosition,
            onValueChange = {
                isDragging = true
                sliderPosition = it
            },
            onValueChangeFinished = {
                isDragging = false
                onRelease((sliderPosition * duration).toLong())
            },
            colors = SliderDefaults.colors(
                activeTrackColor = OnGreen,
                inactiveTrackColor = DisableMusicIconGray
            ),
            thumb = {},
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            MillisToTimeText(timeMillis = currentPosition, size = currentPositionTextSize)
            MillisToTimeText(timeMillis = totalDuration, size = totalDurationTextSize)

        }
    }
}
