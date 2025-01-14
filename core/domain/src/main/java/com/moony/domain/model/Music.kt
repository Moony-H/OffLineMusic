package com.moony.domain.model


data class Music(
    val id:Long,
    val title: String,
    val musicUrl: String,
    val artist: String,
    val imageUrl: String,
    val lyrics: String,
){
    companion object{
        const val LYRICS_KEY="LYRICS"
    }
}
