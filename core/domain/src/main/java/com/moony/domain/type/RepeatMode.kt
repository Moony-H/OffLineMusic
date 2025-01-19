package com.moony.domain.type

enum class RepeatMode {
    NONE,
    ALL,
    ONE;

    fun next() = RepeatMode.entries[(this.ordinal + 1) % RepeatMode.entries.size]

}
