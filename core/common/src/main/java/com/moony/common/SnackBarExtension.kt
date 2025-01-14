package com.moony.common

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import com.moony.domain.type.SnackBarEvent

val LocalSnackBarBridge = compositionLocalOf<SnackBarBridge> { error("No SnackBarHostState provided") }

suspend fun SnackbarHostState.showSnackBarWithEvent(data: SnackBarEvent){
    showSnackbar(
        message = data.message,
        actionLabel = data.actionLabel,
        duration = SnackbarDuration.Short
    )
}

