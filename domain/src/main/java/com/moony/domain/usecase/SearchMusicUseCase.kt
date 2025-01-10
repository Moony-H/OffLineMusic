package com.moony.domain.usecase

import com.moony.domain.model.Music
import com.moony.domain.repository.MusicRepository

class SearchMusicUseCase(private val musicRepository: MusicRepository) {

    suspend operator fun invoke(query: String): List<Music> {
        return musicRepository.searchMusicByName(query)
    }
}