package com.jikanAPI.domain.usecase

import com.jikanAPI.common.Resource
import com.jikanAPI.domain.model.Anime
import com.jikanAPI.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Anime>> {
        return repository.getAnimeDetails(id)
    }
}
