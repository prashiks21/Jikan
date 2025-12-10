package com.jikanAPI.domain.repository

import com.jikanAPI.common.Resource
import com.jikanAPI.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getTopAnime(): Flow<Resource<List<Anime>>>
    fun getAnimeDetails(id: Int): Flow<Resource<Anime>>
}
