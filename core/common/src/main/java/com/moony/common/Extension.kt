package com.moony.common



fun Any?.toStringOrEmpty(): String = this?.toString() ?: ""
