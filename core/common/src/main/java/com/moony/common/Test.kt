package com.moony.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

operator fun <T> StateFlow<T>.getValue(thisObj: Any?, property: KProperty<*>)=this.value
operator fun <T>MutableStateFlow<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T){
    this.value=value
}
