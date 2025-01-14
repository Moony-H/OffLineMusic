package com.moony.domain.type

sealed class SnackBarEvent {
    abstract val message: String
    abstract val actionLabel: String?

    class ServiceNotReady : SnackBarEvent() {
        override val message: String = "잠시후 다시 시도해주세요."
        override val actionLabel: String? = null
    }

    data class Message(
        override val message: String,
        override val actionLabel: String?,
    ) : SnackBarEvent()
}
